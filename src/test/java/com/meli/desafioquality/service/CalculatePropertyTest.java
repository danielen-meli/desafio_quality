package com.meli.desafioquality.service;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.dto.RoomDto;
import com.meli.desafioquality.exception.PropertyException;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.repository.DistrictRepo;
import com.meli.desafioquality.repository.PropertyRepo;
import com.meli.desafioquality.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CalculatePropertyTest {

    @InjectMocks
    CalculateProperty propertyService;

    @InjectMocks
    DistrictService districtService;

    @Mock
    DistrictRepo districtRepo;

    @Mock
    PropertyRepo propertyRepo;

    @BeforeEach
    public void setup() {
        BDDMockito.when(districtRepo.saveDistrict(ArgumentMatchers.any(District.class)))
                .thenReturn(TestUtil.newDistrictSaved());

        BDDMockito.when(propertyRepo.saveProperty(ArgumentMatchers.any(Property.class)))
                .thenReturn(TestUtil.newProperty());

        BDDMockito.when(propertyRepo.getProperties())
                .thenReturn(TestUtil.newPropertyHash());

        BDDMockito.when(districtRepo.districtExists(ArgumentMatchers.any(String.class)))
                .thenReturn(TestUtil.newDistrictSaved());
    }


    @Test
    void createProperty() {
        District newDistrict = TestUtil.newDistrictSaved();
        districtService.createDistrict(newDistrict);

        PropertyRequest newProperty = TestUtil.newPropertyRequest();
        Property savedProperty = propertyService.createProperty(newProperty);

        assertThat(savedProperty.getId()).isPositive();
        assertThat(newProperty.getId()).isEqualTo(savedProperty.getId());
        verify(propertyRepo, atLeastOnce()).saveProperty(ArgumentMatchers.any(Property.class));
    }

    @Test
    void calculateSqrFtgProp() {
        Property newProperty = TestUtil.newProperty();
        double value = propertyService.calculateSqrFtgProp(newProperty.getId());

        double totalArea = newProperty.getListRoom().stream().mapToDouble(r -> r.getWidth() * r.getLength()).sum();

        assertThat(value).isEqualTo(totalArea);
        verify(propertyRepo, atLeastOnce()).getProperties();

    }

    @Test
    void calculatePrice() {
        Property newProperty = TestUtil.newProperty();
        double totalArea = propertyService.calculateSqrFtgProp(newProperty.getId());

        double price = propertyService.calculatePrice(newProperty.getId());

        assertThat(price).isEqualTo(totalArea * newProperty.getDistrict().getSquareMeterValue());
        verify(propertyRepo, atLeastOnce()).getProperties();
    }

    @Test
    void calculateSqrFtgRoom() {
        Property newProperty = TestUtil.newProperty();
        List<RoomDto> totalAreaRoom = propertyService.calculateSqrFtgRoom(newProperty.getId());
        List<RoomDto> listRoomUtil = TestUtil.listRoomDto();

       for(int i = 0; i <= totalAreaRoom.size() - 1; i++) {
          assertThat(totalAreaRoom.get(i).compareTo(listRoomUtil.get(i)) == 0).isTrue();
      }
    }

    @Test
    void largestRoom() {
    }

    @Test
    void getAllDistricts() {
    }

    @Test
    void createDistrict() {
        District newDistrict = TestUtil.newDistrictSaved();
        District savedDistrict = districtService.createDistrict(newDistrict);

        assertThat(newDistrict.getDistrictName()).isEqualTo(savedDistrict.getDistrictName());
        verify(districtRepo, atLeastOnce()).saveDistrict(newDistrict);
    }

    @Test
    void verifyDistrict_throwExcpetion(){
        PropertyRequest newProperty = TestUtil.newPropertyRequest();
        newProperty.setDistrict(null);

        PropertyException exception = Assertions.assertThrows(PropertyException.class, ()->{
            propertyService.verifyDistrict(newProperty);
        });

        assertThat(exception.getMessage()).contains("O bairro não existe");
    }
}