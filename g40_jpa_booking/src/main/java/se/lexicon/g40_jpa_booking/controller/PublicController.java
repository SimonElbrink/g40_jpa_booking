package se.lexicon.g40_jpa_booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PublicController {


    @GetMapping(value = {"/","/index"})
    public String getIndexPage(){
        return "index";
    }


}
