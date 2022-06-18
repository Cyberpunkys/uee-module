package ru.vstu.ueemodule.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.ueemodule.domain.Charter;
import ru.vstu.ueemodule.domain.Student;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.repository.CharterRepository;
import ru.vstu.ueemodule.utils.FileUploadingUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class CharterService {

    private final CharterRepository charterRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public CharterService(CharterRepository charterRepository) {
        this.charterRepository = charterRepository;
    }

    public List<Charter> findAll() {
        return charterRepository.findAll();
    }

    public List<Charter> findByStudent(Student currentStudent) {
        return charterRepository.findByStudent(currentStudent);
    }

    public void createCharter(Charter charter, User user, MultipartFile charterFile) throws IOException {
        String originalFilename = charterFile.getOriginalFilename();
        String latinFilename = FileUploadingUtils.cyr2lat(originalFilename.toLowerCase(Locale.ROOT));
        String resultFilename = FileUploadingUtils.uploadFile(charterFile, latinFilename, uploadPath);
        charter.setFilename(resultFilename);
        charter.setStudent(user.getStudent());

        charterRepository.save(charter);
    }
}
