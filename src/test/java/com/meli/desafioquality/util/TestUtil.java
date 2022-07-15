package com.meli.desafioquality.util;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.dto.RoomDto;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.model.Room;
import com.meli.desafioquality.repository.DistrictRepo;
import com.meli.desafioquality.repository.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtil {

    public static District newDistrictSaved () {

        District district1 = District.builder()
                .districtName("District1")
                .squareMeterValue(55.0)
                .build();

        return district1;
    }

    public static Property newProperty(){
        List<Room> listRoom = new ArrayList<>();
        listRoom.add(new Room("Bedroom", 3, 4));
        listRoom.add(new Room("Kitchen", 4, 4));
        listRoom.add(new Room("Living Room", 5, 3));

        District district = newDistrictSaved();

        return Property.builder()
                .id(1L)
                .name("Prop1")
                .district(district)
                .listRoom(listRoom)
                .build();
    }

    public static PropertyRequest newPropertyRequest(){
        List<Room> listRoom = new ArrayList<>();
        listRoom.add(new Room("Bedroom", 3, 4));
        listRoom.add(new Room("Kitchen", 4, 4));
        listRoom.add(new Room("Living Room", 5, 3));

        District district = newDistrictSaved();

        return PropertyRequest.builder()
                .id(1L)
                .name("Prop1")
                .district(district.getDistrictName())
                .listRoom(listRoom)
                .build();
    }

    public static List<RoomDto> listRoomDto(){
        List<RoomDto> listRoomDto = new ArrayList<>();
        listRoomDto.add(new RoomDto("Bedroom", 12));
        listRoomDto.add(new RoomDto("Kitchen",16));
        listRoomDto.add(new RoomDto("Living Room",15));

        return listRoomDto;
    }

    public static HashMap<Long, Property> newPropertyHash(){
        Property property = newProperty();
        HashMap<Long, Property> hashProperty = new HashMap<>();
        hashProperty.put(property.getId(), property);
        return hashProperty;
    }

    public static List<District> getAllDistricts() {
        List<District> districtList = new ArrayList<District>();
        District district = newDistrictSaved();
        districtList.add(district);
        return districtList;
    }
}
