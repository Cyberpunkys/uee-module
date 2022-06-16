package ru.vstu.ueemodule.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.ueemodule.domain.Coursework;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.CourseworkRepository;
import ru.vstu.ueemodule.utils.FileUploadingUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class CourseworkService {

    private final CourseworkRepository courseworkRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public CourseworkService(CourseworkRepository courseworkRepository) {
        this.courseworkRepository = courseworkRepository;
    }

    public List<Coursework> findAll() {
        return courseworkRepository.findAll();
    }

    public List<Coursework> findByStudent(Student student) {
        return courseworkRepository.findByStudent(student);
    }

    public void uploadCoursework(Coursework coursework, User user, MultipartFile archive) throws IOException {
        Student currentStudent = user.getStudent();
        String filename = FileUploadingUtils.cyr2lat(archive.getOriginalFilename().toLowerCase(Locale.ROOT));
        String resultFilename = FileUploadingUtils.uploadFile(archive, filename, uploadPath);
        coursework.setFilename(resultFilename);
        coursework.setStudent(currentStudent);

        courseworkRepository.save(coursework);
    }
}
