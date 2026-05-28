package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String patients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patlist";
    }

    @GetMapping("/add")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute("patient") Patient patient, Model model) {
        if (patient.getBirth() != null && patient.getBirth().isAfter(LocalDate.now())) {
            model.addAttribute("patient", patient);
            model.addAttribute("errorMessage", "Geburtsdatum darf nicht in der Zukunft liegen.");
            return "add_patient";
        }

        if (!isValidSvnr(patient)) {
            model.addAttribute("patient", patient);
            model.addAttribute("errorMessage", "Ungueltige Sozialversicherungsnummer.");
            return "add_patient";
        }

        try {
            patientRepository.save(patient);
            return "redirect:/patient/list";
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Datenbankzugriff fehlgeschlagen. Bitte pruefen, ob MySQL laeuft.");
            return "database_error";
        }
    }

    private boolean isValidSvnr(Patient patient) {
        String svnr = patient.getSvnr();

        if (svnr == null || !svnr.matches("\\d{10}")) {
            return false;
        }

        int[] weights = {3, 7, 9, 0, 5, 8, 4, 2, 1, 6};
        int sum = 0;

        for (int i = 0; i < svnr.length(); i++) {
            sum += Character.digit(svnr.charAt(i), 10) * weights[i];
        }

        int checkDigit = sum % 11;
        if (checkDigit == 10 || checkDigit != Character.digit(svnr.charAt(3), 10)) {
            return false;
        }

        if (patient.getBirth() == null) {
            return true;
        }

        String birthPart = patient.getBirth().format(DateTimeFormatter.ofPattern("ddMMyy"));
        return svnr.substring(4).equals(birthPart);
    }
}
