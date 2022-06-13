package ru.vstu.ueemodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vstu.ueemodule.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
