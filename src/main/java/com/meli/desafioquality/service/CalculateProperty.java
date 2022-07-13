package com.meli.desafioquality.service;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.exception.PropertyException;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.model.Room;
import com.meli.desafioquality.dto.RoomDto;
import com.meli.desafioquality.repository.DistrictRepo;
import com.meli.desafioquality.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalculateProperty implements ICalculateProperty {
    @Autowired
    PropertyRepo propertyRepo;

    @Autowired
    DistrictRepo districtRepo;



    @Override
    public Property createProperty(PropertyRequest propertyRequest) {
        return propertyRepo.saveProperty(verifyDistrict(propertyRequest));
    }

    @Override
    public double calculateSqrFtgProp(Long id) {  // 01
        Property property = propertyRepo.getProperties().get(id);

        List<Room> room = property.getListRoom();

        return room.stream().mapToDouble(r -> r.getWidth() * r.getLength()).sum();
    }

    @Override
    public double calculatePrice(Long id) {  // 02
        double totalArea =  calculateSqrFtgProp(id);
        Property property = propertyRepo.getProperties().get(id);
        return totalArea * property.getDistrict().getSquareMeterValue();
    }

    @Override
    public List<RoomDto> calculateSqrFtgRoom(Long id) { // 04
        Property property = propertyRepo.getProperties().get(id);

        return property.getListRoom().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @Override
    public RoomDto largestRoom(Long id) {
        List<RoomDto> listRoom = calculateSqrFtgRoom(id);

        return listRoom.stream().max(Comparator.comparing(RoomDto::getArea)).get();
    }

    private Property verifyDistrict(PropertyRequest request){

        District district = districtRepo.districtExists(request.getDistrict());

        if(district != null){
            return Property.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .district(district)
                    .listRoom(request.getListRoom())
                    .build();
        }
        throw new PropertyException("O bairro não existe");

    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepo.getDistricts();
    }


}
