package se.lexicon.g40_jpa_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g40_jpa_booking.model.dto.form.PremisesForm;
import se.lexicon.g40_jpa_booking.model.dto.view.PremisesDTO;
import se.lexicon.g40_jpa_booking.model.entity.Premises;
import se.lexicon.g40_jpa_booking.service.entity.PremisesEntityService;
import se.lexicon.g40_jpa_booking.service.facade.EntityToDTOConverter;
import se.lexicon.g40_jpa_booking.service.facade.PremisesService;
import se.lexicon.g40_jpa_booking.validation.OnPost;

import java.util.List;

@RestController
public class PremisesController {

    private final PremisesEntityService premisesEntityService;

    private final PremisesService premisesService;

    @Autowired
    public PremisesController(PremisesEntityService premisesEntityService, PremisesService premisesService) {
        this.premisesEntityService = premisesEntityService;
        this.premisesService = premisesService;
    }

    @GetMapping("/api/v1/premises")
    public ResponseEntity<List<PremisesDTO>> getPremises(){

        List<PremisesDTO> all = premisesService.findAll();

        if (all.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(all);
        }
    }

    @GetMapping("/api/v1/premises/{id}")
    public ResponseEntity<PremisesDTO> findById(@PathVariable(value = "id") String premisesId){
        return ResponseEntity.ok(premisesService.findById(premisesId));
    }

    @PostMapping("/api/v1/premises")
    public ResponseEntity<Premises> create(@Validated(OnPost.class) @RequestBody PremisesForm premisesForm){
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
