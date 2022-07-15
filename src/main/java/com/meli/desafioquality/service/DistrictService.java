package com.meli.desafioquality.service;

import com.meli.desafioquality.model.District;
import com.meli.desafioquality.repository.DistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe Service de bairro.
 */
@Service
public class DistrictService implements IDistrictService{

    @Autowired
    DistrictRepo districtRepo;

    /** Retorna lista de bairros cadastrados.
     * @return List<District>
     */
    @Override
    public List<District> getAllDistricts() {
        return districtRepo.getDistricts();
    }

    /** Cria um bairro.
     * @param district
     * @return District
     */
    @Override
    public District createDistrict(District district) {
        return districtRepo.saveDistrict(district);
    }

}
