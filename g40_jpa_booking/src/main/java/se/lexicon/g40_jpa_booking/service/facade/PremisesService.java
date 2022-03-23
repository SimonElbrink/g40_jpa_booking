package se.lexicon.g40_jpa_booking.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.g40_jpa_booking.model.dto.view.PremisesDTO;
import se.lexicon.g40_jpa_booking.model.entity.Premises;
import se.lexicon.g40_jpa_booking.service.entity.PremisesEntityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremisesService {

    private PremisesEntityService premisesEntityService;
    private EntityToDTOConverter converter;

    @Autowired
    public PremisesService(PremisesEntityService premisesEntityService, EntityToDTOConverter converter) {
        this.premisesEntityService = premisesEntityService;
        this.converter = converter;
    }

    public PremisesDTO findById(String id){
        Premises premises = premisesEntityService.findById(id);

        PremisesDTO premisesDTO = converter.toFullPremisesDTO(premises);

        return premisesDTO;
    }

    public List<PremisesDTO> findAll(){
        List<Premises> premisesList = premisesEntityService.findAll();

        List<PremisesDTO> premisesDTOS = premisesList.stream()
                .map(converter::toFullPremisesDTO)
                .collect(Collectors.toList());

        return premisesDTOS;
    }




}
