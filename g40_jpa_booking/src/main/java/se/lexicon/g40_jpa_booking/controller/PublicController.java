package se.lexicon.g40_jpa_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.lexicon.g40_jpa_booking.model.dto.form.AppUserForm;
import se.lexicon.g40_jpa_booking.model.dto.form.ContactInfoForm;
import se.lexicon.g40_jpa_booking.model.dto.form.PatientForm;
import se.lexicon.g40_jpa_booking.model.entity.Patient;
import se.lexicon.g40_jpa_booking.service.entity.PatientEntityService;

@Controller
public class PublicController {

    private PatientEntityService patientEntityService;

    @Autowired
    public PublicController(PatientEntityService patientEntityService) {
        this.patientEntityService = patientEntityService;
    }

    @GetMapping(value = {"/","/index"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping(value = "/public/register")
    public String getPatientRegistrationForm(Model model){
        PatientForm form = new PatientForm();
        form.setContactInfo(new ContactInfoForm());
        form.setUserCredentials(new AppUserForm());
        model.addAttribute("form",form);
        return "patient-form";
    }

    @PostMapping(value = "/public/register/process")
    public String processRegistrationForm(PatientForm form){

        Patient patient = patientEntityService.create(form);

        System.out.println(patient);

        return "redirect:/patient/"+ patient.getId();
    }

}
