package com.meli.desafioquality.controller;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.dto.RoomDto;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.service.ICalculateProperty;
import com.meli.desafioquality.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    ICalculateProperty propertyService;

    /**
     * Registra uma propriedade
     * @param property
     * @return ResponseEntity<Property>
     */
    @PostMapping("/register")
    public ResponseEntity<Property> registerProperty(@RequestBody @Valid PropertyRequest property){

        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperty(property));
    }



    /** Recebe um id e retorna o calculo da área da propriedade indicada.
     * @param id
     * @return  ResponseEntity<Double>
     */
    @GetMapping("/calculateSqrFtgProp/{id}")
    public ResponseEntity<Double> calculateSqrFtgProp(@PathVariable @Valid Long id){
        return ResponseEntity.ok(propertyService.calculateSqrFtgProp(id));
    }

    /**
     * Recebe um id e retorna o maior cômodo da propriedade requisitada pelo id
     * @param id
     * @return ResponseEntity<RoomDto>
     */
    @GetMapping("/largestRoom/{id}")
    public ResponseEntity<RoomDto> largestRoom(@PathVariable @Valid Long id){

        return ResponseEntity.ok(propertyService.largestRoom(id));
    }

    /**
     * Recebe um id e retorna o preço total da propriedade
     * @param id
     * @return ResponseEntity<Double>
     */
    @GetMapping("/calculatePrice/{id}")
    public ResponseEntity<Double> calculatePrice(@PathVariable @Valid Long id){

        return ResponseEntity.ok(propertyService.calculatePrice(id));
    }

    /**
     * Recebe um id e retorna uma lista de cômodos com a área já calculada
     * @param id
     * @return ResponseEntity<List<RoomDto>>
     */
    @GetMapping("/calculateSqrFtgRoom/{id}")
    public ResponseEntity<List<RoomDto>> calculateSqrFtgRoom(@PathVariable @Valid Long id){
        return ResponseEntity.ok(propertyService.calculateSqrFtgRoom(id));
    }
}
