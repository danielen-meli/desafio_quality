package com.meli.desafioquality.repository;

import com.meli.desafioquality.model.District;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class DistrictRepo {
    Map<String, District> listDistrict = new HashMap<String, District>();

    public District saveDistrict(District district){

        listDistrict.put(district.getDistrictName(), district);
        return district;
    }


    public District districtExists(String districtName){
        if(listDistrict.containsKey(districtName)){
           return listDistrict.get(districtName);
        }
        return null;
    }

    public List<District> getDistricts() {
        return this.listDistrict.values().stream().collect(Collectors.toCollection(ArrayList::new));
    }


}
