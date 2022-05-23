package ru.vstu.ueemodule.service;

import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Level;
import ru.vstu.ueemodule.repository.LevelRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LevelService {

    private final LevelRepository levelRepository;

    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public List<Level> findAll() {
        Iterable<Level> repositoryAll = levelRepository.findAll();
        List<Level> levels = new ArrayList<>();
        repositoryAll.forEach(levels::add);

        return levels;
    }

    public Long count() {
        return levelRepository.count();
    }

    public void createLevel(Level level) {
        levelRepository.save(level);
    }
}
