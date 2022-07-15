package com.meli.desafioquality.service;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.dto.RoomDto;

import java.util.List;

public interface ICalculateProperty {
    Property createProperty(PropertyRequest propertyRequest);
    double calculateSqrFtgProp(Long id);
    double calculatePrice(Long id);
    List<RoomDto> calculateSqrFtgRoom(Long id);
    RoomDto largestRoom(Long id);
}
