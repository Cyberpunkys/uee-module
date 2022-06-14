package ru.vstu.ueemodule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.ueemodule.domain.Role;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.StudentRepository;
import ru.vstu.ueemodule.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public UserService(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public String createUser(User user, Model model, String surname, String name, String patronymic, String retype) {

        if (!user.getPassword().equals(retype)) {
            model.addAttribute("errorMessage", "Пароли не совпадают");
            return "registration/registration";
        }

        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("errorMessage", "Такой пользователь уже существует");
            return "registration/registration";
        }

        Student matchedStudentFromDb = studentRepository.findBySurnameAndNameAndPatronymic(surname, name, patronymic);

        if (matchedStudentFromDb == null) {
            model.addAttribute("errorMessage", "Студент с такими ФИО не найден");
            return "registration/registration";
        }

        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        matchedStudentFromDb.setOwner(user);
        studentRepository.save(matchedStudentFromDb);

        return "redirect:/";
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long count() {
        return userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void uploadVaccinationInfo(
            User user,
            MultipartFile certificate,
            LocalDate injectionDate,
            Model model
    ) throws IOException {

        log.info(user.toString());

        Student associatedStudent = user.getStudent();

        log.info(associatedStudent.toString());

        String certificateOriginalFilename = certificate.getOriginalFilename();

        if (certificateOriginalFilename == null || certificateOriginalFilename.isEmpty()) {
            model.addAttribute("successMessage", "Файл не может быть пустым.");
            return;
        }

        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidPrefix = UUID.randomUUID().toString();
        String resultFilename = uuidPrefix + "." + certificateOriginalFilename;
        certificate.transferTo(new File(uploadPath + "/" + resultFilename));

        associatedStudent.setCertificateFilename(resultFilename);
        associatedStudent.setInjectionDate(injectionDate);

        studentRepository.save(associatedStudent);

        model.addAttribute("successMessage",
                "Сертификат успешно загружен. Администратор вскоре его проверит.");
    }
}
