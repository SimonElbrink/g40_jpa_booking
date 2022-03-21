package se.lexicon.g40_jpa_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.lexicon.g40_jpa_booking.model.dto.form.AppUserForm;
import se.lexicon.g40_jpa_booking.model.dto.form.ContactInfoForm;
import se.lexicon.g40_jpa_booking.model.dto.form.PatientForm;
import se.lexicon.g40_jpa_booking.model.entity.Patient;
import se.lexicon.g40_jpa_booking.service.entity.PatientEntityService;
import se.lexicon.g40_jpa_booking.validation.OnPost;

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
    public String processRegistrationForm(@Validated(value = OnPost.class) @ModelAttribute("form") PatientForm form, BindingResult bindingResult){

        if (!form.getUserCredentials().getPassword().equals(form.getUserCredentials().getPasswordConfirm())){
            FieldError fieldError = new FieldError("form","userCredentials.passwordConfirm", "Passwords does not match!");
            bindingResult.addError(fieldError);
        }

        if (bindingResult.hasErrors()){
            return "patient-form";
        }

        Patient patient = patientEntityService.create(form);

        return "redirect:/patient/"+ patient.getId();
    }

    @PostMapping(value = "/public/register/process2")
    public String processRegistrationForm2( @ModelAttribute("form") PatientForm form, @RequestParam(name = "access", defaultValue = "true") boolean access){

        System.out.println(access);

        Patient patient = patientEntityService.create(form);

        return "redirect:/patient/"+ patient.getId();
    }



}
