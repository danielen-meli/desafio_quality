package com.meli.desafioquality.controller;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.service.ICalculateProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    ICalculateProperty propertyService;

    @PostMapping("/register")
    public ResponseEntity<Property> registerProperty(@RequestBody @Valid PropertyRequest property){
        return null;
    }


    @GetMapping("/totalArea/${id}")
    public ResponseEntity<Property> getTotalArea(@PathVariable @Valid Long id){
        return null;
    }

    @GetMapping("/biggestRoom/${id}")
    public ResponseEntity<Property> biggestRoom(@PathVariable @Valid Long id){
        return null;
    }

    @GetMapping("//${id}")
    public ResponseEntity<Property> biggestRoom(@PathVariable @Valid Long id){
        return null;
    }






}
