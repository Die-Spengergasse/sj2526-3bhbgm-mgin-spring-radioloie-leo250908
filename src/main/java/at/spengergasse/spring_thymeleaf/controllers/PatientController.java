package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

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
        if (patient.getSvnr() == null || !patient.getSvnr().matches("\\d{10}")) {
            model.addAttribute("patient", patient);
            model.addAttribute("errorMessage", "SVNR muss genau 10 Ziffern lang sein.");
            return "add_patient";
        }

        if (patient.getBirth() != null && patient.getBirth().isAfter(LocalDate.now())) {
            model.addAttribute("patient", patient);
            model.addAttribute("errorMessage", "Geburtsdatum darf nicht in der Zukunft liegen.");
            return "add_patient";
        }

        patientRepository.save(patient);
        return "redirect:/patient/list";
    }
}
