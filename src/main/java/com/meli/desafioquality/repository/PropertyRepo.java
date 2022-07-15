package com.meli.desafioquality.repository;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public class PropertyRepo {

    HashMap<Long, Property> listProperties = new HashMap<>();

    public Property saveProperty(Property property){
        listProperties.put(property.getId(), property);
        return property;
    }

    public HashMap<Long, Property> getProperties(){
        return listProperties;
    }

    public void clearProperties() {
        this.listProperties.clear();
    }

}
