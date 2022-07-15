package com.meli.desafioquality.service;

import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.repository.DistrictRepo;
import com.meli.desafioquality.repository.PropertyRepo;
import com.meli.desafioquality.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DistrictServiceTest {

    @InjectMocks
    DistrictService districtService;

    @Mock
    DistrictRepo districtRepo;

    @BeforeEach
    public void setup() {
        BDDMockito.when(districtRepo.saveDistrict(ArgumentMatchers.any(District.class)))
                .thenReturn(TestUtil.newDistrictSaved());

        BDDMockito.when(districtRepo.getDistricts())
                .thenReturn(TestUtil.getAllDistricts());
    }

    @Test
    @DisplayName("Testa se retorna a lista de bairros.")
    void getAllDistricts() {
        List<District> districtListUtil = TestUtil.getAllDistricts();
        List<District> allDistrictsFromService = districtService.getAllDistricts();

        assertThat(districtListUtil.size()).isEqualTo(allDistrictsFromService.size());
        assertThat(districtListUtil.get(0).getDistrictName()).isEqualTo(allDistrictsFromService.get(0).getDistrictName());
    }

    @Test
    @DisplayName("Testa o método que cria um bairro. ")
    void createDistrict() {
        District newDistrict = TestUtil.newDistrictSaved();
        District savedDistrict = districtService.createDistrict(newDistrict);

        assertThat(newDistrict.getDistrictName()).isEqualTo(savedDistrict.getDistrictName());
        verify(districtRepo, atLeastOnce()).saveDistrict(newDistrict);
    }
}