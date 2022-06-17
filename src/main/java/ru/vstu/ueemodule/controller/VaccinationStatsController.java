package ru.vstu.ueemodule.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vstu.ueemodule.repository.StudentRepository;

import java.time.LocalDate;

@Controller
@RequestMapping("/vaccinationStats")
@PreAuthorize("hasAuthority('ADMIN')")
public class VaccinationStatsController {

    private final StudentRepository studentRepository;

    public VaccinationStatsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping()
    public String test(Model model) {
        int currentYear = LocalDate.now().getYear();
        int pastYear = currentYear - 1;
        long studentsVaccinated = studentRepository.countStudentsByCertificateFilenameIsNotNull();
        long studentsAll = studentRepository.count();

        long vaccineCurrYear = studentRepository.
                countStudentsByInjectionDateAfter(LocalDate.of(currentYear, 1, 1));

        long vaccinePastYear = studentRepository.
                countStudentsByInjectionDateBetween(
                        LocalDate.of(pastYear, 1, 1),
                        LocalDate.of(currentYear, 1, 1)
                );

        long vaccinesOld = studentRepository.
                countStudentsByInjectionDateBefore(LocalDate.of(pastYear, 1, 1));

        long activeCerts = studentRepository.
                countStudentsByInjectionDateBetween(LocalDate.now().minusYears(1), LocalDate.now());

        long expiredCerts = studentsVaccinated - activeCerts;

        model.addAttribute("studentsVaccinated", studentsVaccinated);
        model.addAttribute("vaccineCurrYear", vaccineCurrYear);
        model.addAttribute("vaccinePastYear", vaccinePastYear);
        model.addAttribute("studsCnt", studentsAll);
        model.addAttribute("vaccinesOld", vaccinesOld);
        model.addAttribute("percent", Math.round(1.0 * studentsVaccinated / studentsAll * 100));
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("pastYear", pastYear);
        model.addAttribute("activeCerts", activeCerts);
        model.addAttribute("expiredCerts", expiredCerts);

        return "stats/vaccinationStats";
    }
}
