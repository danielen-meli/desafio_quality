package com.meli.desafioquality.service;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.dto.RoomDto;

import java.util.List;

public interface ICalculateProperty {
    Property createProperty(PropertyRequest propertyRequest);
    double calculateSqrFtgProp(Long id); //us-0001
    double calculatePrice(Long id); //us-0002
    List<RoomDto> calculateSqrFtgRoom(Long id); // us-0004
    RoomDto largestRoom(Long id); // us-0003
    List<District> getAllDistricts();
    District createDistrict(District district);
}
