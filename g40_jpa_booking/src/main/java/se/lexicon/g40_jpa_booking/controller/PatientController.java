package se.lexicon.g40_jpa_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.lexicon.g40_jpa_booking.model.entity.Patient;
import se.lexicon.g40_jpa_booking.service.entity.PatientEntityService;

@Controller
public class PatientController {

    private final PatientEntityService patientEntityService;

    @Autowired
    public PatientController(PatientEntityService patientEntityService) {
        this.patientEntityService = patientEntityService;
    }

    @GetMapping(value = "/patient/{id}")
    public String findPatientById(@PathVariable("id") String id, Model model){
        Patient patient = patientEntityService.findById(id);

        model.addAttribute("patient",patient);
        return "patient";
    }
}
