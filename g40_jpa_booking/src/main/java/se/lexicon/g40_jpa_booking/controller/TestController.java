package se.lexicon.g40_jpa_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloMessage(){
        return "Hello World";
    }

    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public String helloMessage(@RequestParam(value = "message", defaultValue = "Hello World!") String customMessage){
        return customMessage;
    }



}
