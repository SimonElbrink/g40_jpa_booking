package se.lexicon.g40_jpa_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g40_jpa_booking.model.dto.form.PremisesForm;
import se.lexicon.g40_jpa_booking.model.entity.Premises;
import se.lexicon.g40_jpa_booking.service.entity.PremisesEntityService;

import java.util.List;

@RestController
public class PremisesController {

    private final PremisesEntityService premisesEntityService;

    @Autowired
    public PremisesController(PremisesEntityService premisesEntityService) {
        this.premisesEntityService = premisesEntityService;
    }

    @GetMapping("/api/v1/premises")
    public ResponseEntity<List<Premises>> getPremises(){

        List<Premises> all = premisesEntityService.findAll();

        if (all.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(all);
        }
    }

    @GetMapping("/api/v1/premises/{id}")
    public ResponseEntity<Premises> findById(@PathVariable(value = "id") String premisesId){
        Premises foundById = premisesEntityService.findById(premisesId);
        return ResponseEntity.ok(foundById);
    }

    @PostMapping("/api/v1/premises")
    public ResponseEntity<Premises> create(@RequestBody PremisesForm premisesForm){
        Premises premises = premisesEntityService.create(premisesForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(premises);
    }

    @PutMapping("/api/v1/premises/{id}")
    public ResponseEntity<Premises> update(@PathVariable("id") String premisesId, @RequestBody PremisesForm premisesForm){
        return ResponseEntity.ok(premisesEntityService.update(premisesId,premisesForm));
    }

    @DeleteMapping("/api/v1/premises/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String premisesId){
        premisesEntityService.delete(premisesId);
        return ResponseEntity.noContent().build();

    }
}
