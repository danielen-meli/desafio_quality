package com.meli.desafioquality.service;

import com.meli.desafioquality.model.District;
import com.meli.desafioquality.repository.DistrictRepo;
import com.meli.desafioquality.repository.PropertyRepo;
import com.meli.desafioquality.util.TestUtil;
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
    }


    @Test
    void createProperty() {
    }

    @Test
    void calculateSqrFtgProp() {
    }

    @Test
    void calculatePrice() {
    }

    @Test
    void calculateSqrFtgRoom() {
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
}