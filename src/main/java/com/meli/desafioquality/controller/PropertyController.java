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

    @PostMapping("/register")
    public ResponseEntity<Property> registerProperty(@RequestBody @Valid PropertyRequest property){

        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createProperty(property));
    }


    @GetMapping("/calculateSqrFtgProp/{id}")
    public ResponseEntity<Double> calculateSqrFtgProp(@PathVariable @Valid Long id){
        return ResponseEntity.ok(propertyService.calculateSqrFtgProp(id));
    }

    @GetMapping("/largestRoom/{id}")
    public ResponseEntity<RoomDto> largestRoom(@PathVariable @Valid Long id){

        return ResponseEntity.ok(propertyService.largestRoom(id));
    }

    @GetMapping("/calculatePrice/{id}")
    public ResponseEntity<Double> calculatePrice(@PathVariable @Valid Long id){

        return ResponseEntity.ok(propertyService.calculatePrice(id));
    }

    @GetMapping("/calculateSqrFtgRoom/{id}")
    public ResponseEntity<List<RoomDto>> calculateSqrFtgRoom(@PathVariable @Valid Long id){
        return ResponseEntity.ok(propertyService.calculateSqrFtgRoom(id));
    }


//    @GetMapping("/districts")
//    public ResponseEntity<List<District>> getAllDistricts() {
//        return ResponseEntity.ok(propertyService.getAllDistricts());
//    }
//
//    @PostMapping("/registerDistrict")
//    public ResponseEntity<District> registerDistrict(@RequestBody @Valid District district){
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.createDistrict(district));
//    }



}
