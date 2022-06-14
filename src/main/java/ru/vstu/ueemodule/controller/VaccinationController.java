package ru.vstu.ueemodule.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.vstu.ueemodule.domain.User;
import ru.vstu.ueemodule.service.UserService;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;

@Controller
@RequestMapping("/vaccination")
public class VaccinationController {

    private final UserService userService;

    public VaccinationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String vaccinationUploadPage() {
        return "vaccination/vaccination";
    }

    @PostMapping
    public String uploadVaccinationData(
            @AuthenticationPrincipal User user,
            @RequestParam("certificate") MultipartFile certificate,
            @RequestParam("injectionDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate injectionDate,
            Model model
    ) throws IOException {
        userService.uploadVaccinationInfo(user, certificate, injectionDate, model);

        return "vaccination/vaccination";
    }
}
