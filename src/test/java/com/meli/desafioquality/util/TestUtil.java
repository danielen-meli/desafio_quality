package com.meli.desafioquality.util;

import com.meli.desafioquality.model.District;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtil {



    public static District newDistrictSaved () {

//        Map<String, District> listDistrict = new HashMap<>();

        District district1 = District.builder()
                .districtName("District1")
                .squareMeterValue(55.0)
                .build();

//        listDistrict.put(district1.getDistrictName(), district1);

        return district1;
    }



}
