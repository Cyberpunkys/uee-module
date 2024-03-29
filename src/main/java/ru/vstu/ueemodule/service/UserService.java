package ru.vstu.ueemodule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.ueemodule.domain.Role;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.StudentRepository;
import ru.vstu.ueemodule.repository.UserRepository;
import ru.vstu.ueemodule.utils.FileUploadingUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${upload.path}")
    private String uploadPath;

    public UserService(
            UserRepository userRepository,
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        Student associatedStudent = user.getStudent();
        associatedStudent.setCertificateFilename(null);
        associatedStudent.setInjectionDate(null);

        String certificateOriginalFilename = certificate.getOriginalFilename();

        if (certificateOriginalFilename == null || certificateOriginalFilename.isEmpty()) {
            model.addAttribute("infoMessage", "Файл не может быть пустым.");
            return;
        }

        String latinFilename = FileUploadingUtils.cyr2lat(certificateOriginalFilename.toLowerCase(Locale.ROOT));
        String resultFilename = FileUploadingUtils.uploadFile(certificate, latinFilename, uploadPath);

        associatedStudent.setCertificateFilename(resultFilename);
        associatedStudent.setInjectionDate(injectionDate);

        studentRepository.save(associatedStudent);

        model.addAttribute("infoMessage",
                "Сертификат успешно загружен.");
    }
}
