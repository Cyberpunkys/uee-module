package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Charter;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.CharterRepository;

import java.util.List;

@Service
public class CharterService {

    private final CharterRepository charterRepository;

    public CharterService(CharterRepository charterRepository) {
        this.charterRepository = charterRepository;
    }

    public List<Charter> findAll() {
        return charterRepository.findAll();
    }

    public List<Charter> findByStudent(Student currentStudent) {
        return charterRepository.findByStudent(currentStudent);
    }

    public void createCharter(Charter charter, User user) {
        charter.setStudent(user.getStudent());
        charterRepository.save(charter);
    }
}
