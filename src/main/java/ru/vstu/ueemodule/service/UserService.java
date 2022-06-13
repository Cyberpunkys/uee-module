package ru.vstu.ueemodule.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.vstu.ueemodule.domain.Role;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.StudentRepository;
import ru.vstu.ueemodule.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

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
}
