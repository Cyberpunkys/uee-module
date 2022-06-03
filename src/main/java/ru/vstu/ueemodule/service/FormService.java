package ru.vstu.ueemodule.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.vstu.ueemodule.domain.Form;
import ru.vstu.ueemodule.repository.FormRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public List<Form> findAll() {
        Iterable<Form> repositoryAll = formRepository.findAll();
        List<Form> forms = new ArrayList<>();
        repositoryAll.forEach(forms::add);

        return forms;
    }

    public Long count() {
        return formRepository.count();
    }

    public void createForm(Form form) {
        formRepository.save(form);
    }

    public Form getOne(Integer id) {
        return formRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void editForm(Integer id, Form editedForm) {
        Form formFromDb = getOne(id);
        BeanUtils.copyProperties(editedForm, formFromDb, "id");
        formRepository.save(formFromDb);
    }
}
