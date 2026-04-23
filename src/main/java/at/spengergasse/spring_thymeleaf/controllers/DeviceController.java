package at.spengergasse.spring_thymeleaf.controllers;

public class DeviceController {
}


//import at.spengergasse.spring_thymeleaf.entities.Device;
//import at.spengergasse.spring_thymeleaf.entities.DeviceRepository;
//import at.spengergasse.spring_thymeleaf.entities.Patient;
//import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/device")
//public class DeviceController
//{
//    private final DeviceRepository deviceRepository;
//
//    public DeviceController(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
//
//    @GetMapping("/list")
//    public String device(Model model) {
//        model.addAttribute("device", deviceRepository.findAll());
//        return "patlist";
//    }
//
//    @GetMapping("/add")
//    public String addDevice(Model model) {
//        model.addAttribute("device", new Device());
//        return "add_device";
//    }
//
//    @PostMapping("/add")
//    public String addDevice(@ModelAttribute("device") Device device) {
//        deviceRepository.save(device);
//        return  "redirect:/device/list";
//    }
//}
