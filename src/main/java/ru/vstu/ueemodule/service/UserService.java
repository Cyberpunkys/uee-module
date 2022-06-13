package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.vstu.ueemodule.domain.Role;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("errorMessage", "Такой пользователь уже существует");
            return "registration/registration";
        }

        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/";
    }
}
