



//package at.spengergasse.spring_thymeleaf.controllers;
//
//import at.spengergasse.spring_thymeleaf.entities.Patient;
//import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
//import at.spengergasse.spring_thymeleaf.entities.Reservierung;
//import at.spengergasse.spring_thymeleaf.entities.ReservierungRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/reservierung")
//public class ReservierungController
//{
//    private final ReservierungRepository reservierungrepository;
//
//    public ReservierungController(ReservierungRepository reservierungrepository) {
//        this.reservierungrepository = reservierungrepository;
//    }
//
//    @GetMapping("/list")
//    public String reservierung(Model model)
//    {
//        model.addAttribute("reservierung",reservierungrepository.findAll());
//        return "reslist";
//    }
//    @GetMapping("/add")
//    public String addReservation(Model model)
//    {
//        model.addAttribute("reservierung",new Reservierung());
//        return "add_reservation";
//    }
//
//    @PostMapping("/list")
//    public String addReservation(@ModelAttribute("reservierung") Reservierung reservierung)
//    {
//        reservierungrepository.save(reservierung);
//        return "redirect:/reservierung/list";
//    }
//}

package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationRepository reservationrepository;
    private final PatientRepository patientrepository;
    private final DeviceRepository deviceRepository;

    public ReservationController(ReservationRepository reservationrepository, PatientRepository patientrepository, DeviceRepository deviceRepository) {
        this.reservationrepository = reservationrepository;
        this.patientrepository = patientrepository;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/list")
    public String reservations(Model model, Integer deviceId) {
        if (deviceId != null) {
            model.addAttribute("reservations", reservationrepository.findByDeviceId(deviceId));
        } else {
            model.addAttribute("reservations", reservationrepository.findAll());
        }
        model.addAttribute("devices", deviceRepository.findAll());
        return "reslist";
    }

    @GetMapping("/add")
    public String addReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("patients", patientrepository.findAll());
        model.addAttribute("devices", deviceRepository.findAll());
        return "add_reservation";
    }

    @PostMapping("/add")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation,
                                 @RequestParam("patientId") Integer patientId,
                                 @RequestParam("deviceId") Integer deviceId,
                                 Model model) {
        Patient patient = patientrepository.findById(patientId).orElse(null);
        Device device = deviceRepository.findById(deviceId).orElse(null);

        if (patient == null || device == null) {
            model.addAttribute("reservation", reservation);
            model.addAttribute("patients", patientrepository.findAll());
            model.addAttribute("devices", deviceRepository.findAll());
            model.addAttribute("errorMessage", "Patient oder Geraet konnte nicht gefunden werden.");
            return "add_reservation";
        }

        reservation.setPatient(patient);
        reservation.setDevice(device);
        reservationrepository.save(reservation);
        return "redirect:/reservation/list";
    }

}


