package ru.vstu.ueemodule.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Speciality;
import ru.vstu.ueemodule.repository.SpecialityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<Speciality> findAll() {
        Iterable<Speciality> repositoryAll = specialityRepository.findAll();
        List<Speciality> specialities = new ArrayList<>();
        repositoryAll.forEach(specialities::add);

        return specialities;
    }

    public Long count() {
        return specialityRepository.count();
    }

    public void createSpeciality(Speciality speciality) {
        specialityRepository.save(speciality);
    }

    public void editSpeciality(Speciality specialityFromDb, Speciality editedLevel) {
        BeanUtils.copyProperties(editedLevel, specialityFromDb);
        specialityRepository.save(specialityFromDb);
    }

    public void deleteSpeciality(Speciality specialityToDelete) {
        specialityRepository.delete(specialityToDelete);
    }
}
