package com.meli.desafioquality.integration;

import com.meli.desafioquality.dto.PropertyRequest;
import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Property;
import com.meli.desafioquality.repository.DistrictRepo;
import com.meli.desafioquality.repository.PropertyRepo;
import com.meli.desafioquality.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    DistrictRepo districtRepo;

    @Autowired
    PropertyRepo propertyRepo;

    @BeforeEach
    public void setup() {
        districtRepo.clearDistricts();
        propertyRepo.clearProperties();
    }

    @Test
    @DisplayName("Testa se a rota post funciona para adicionar um distrito")
    public void registerADistrict() {
        District newDistrict = TestUtil.newDistrictSaved();
        String baseUrl = "http://localhost:" + port + "/district";
        HttpEntity<District> httpEntity = new HttpEntity<>(newDistrict);

        ResponseEntity<District> districtCreatedReturn =
                testRestTemplate.exchange(baseUrl + "/registerDistrict",
                        HttpMethod.POST, httpEntity, District.class);

        District districtCreatedBody = districtCreatedReturn.getBody();

        assertThat(districtCreatedReturn.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(districtCreatedBody).isNotNull();
        assertThat(districtCreatedBody.getDistrictName()).isEqualTo(newDistrict.getDistrictName());
        assertThat(districtCreatedBody.getSquareMeterValue()).isEqualTo(newDistrict.getSquareMeterValue());
    }

    @Test
    @DisplayName("Testa quando a lista de bairros está vazia")
    public void getAllDistricts() {
        List<District> allDistricts = TestUtil.getAllDistricts();
        String baseUrl = "http://localhost:" + port + "/district";

        ResponseEntity<District[]> districtsReturn =
                testRestTemplate.exchange(baseUrl,
                        HttpMethod.GET, null, District[].class);

        District[] districtBody = districtsReturn.getBody();

        assertThat(districtsReturn.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(districtBody).isNotNull();
        assertThat(districtBody).isEmpty();
    }

    @Test
    @DisplayName("Testa quando a lista de bairros tem ao menos um registro")
    public void getAllDistrictsWithResults() {
        List<District> allDistricts = TestUtil.getAllDistricts();
        String baseUrl = "http://localhost:" + port + "/district";

        ResponseEntity<District[]> districtsReturn =
                testRestTemplate.exchange(baseUrl,
                        HttpMethod.GET, null, District[].class);

        District[] districtBody = districtsReturn.getBody();

        assertThat(districtsReturn.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(districtBody).isNotNull();
        assertThat(districtBody).isEmpty();

        District newDistrict = TestUtil.newDistrictSaved();
        HttpEntity<District> httpEntity = new HttpEntity<>(newDistrict);

        ResponseEntity<District> districtCreatedReturn =
                testRestTemplate.exchange(baseUrl + "/registerDistrict",
                        HttpMethod.POST, httpEntity, District.class);


        districtsReturn =
                testRestTemplate.exchange(baseUrl,
                        HttpMethod.GET, null, District[].class);

        District[] districtCreatedBody = districtsReturn.getBody();

        assertThat(districtCreatedReturn.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(districtCreatedBody).isNotNull();
        assertThat(districtCreatedBody[0].getDistrictName()).isEqualTo(newDistrict.getDistrictName());
        assertThat(districtCreatedBody[0].getSquareMeterValue()).isEqualTo(newDistrict.getSquareMeterValue());
    }

   // properties/register    -> POST new property (precisa criar um distrito antes)

   // properties/calculateSqrFtgProp/1   -> GET Responde com a area total somada de todos os comodos

   // properties/largestRoom/1          -> GET retorna o maior comodo

   // properties/calculatePrice/1       -> GET Calcula o preço total da propriedade (metrosTotal * valorMetro)

   // properties/calculateSqrFtgRoom/1   -> GET area total de cada comodo


    @Test
    @DisplayName("Testa se a rota post funciona para adicionar um imóvel")
    public void registerAProperty(){
        String baseUrlDistrict = "http://localhost:" + port + "/district";
        District newDistrict = TestUtil.newDistrictSaved();
        HttpEntity<District> httpEntityDistrict = new HttpEntity<>(newDistrict);

        // add o distrito
        ResponseEntity<District> districtCreatedReturn =
                testRestTemplate.exchange(baseUrlDistrict + "/registerDistrict",
                        HttpMethod.POST, httpEntityDistrict, District.class);


        // add a propriedade
        PropertyRequest newProperty = TestUtil.newPropertyRequest();
        String baseUrl = "http://localhost:" + port + "/properties/register";
        HttpEntity<PropertyRequest> httpEntity = new HttpEntity<>(newProperty);



        ResponseEntity<Property> propertyReturn =
                testRestTemplate.exchange(baseUrl,
                        HttpMethod.POST, httpEntity, Property.class);

        Property propertyBody = propertyReturn.getBody();

        assertThat(propertyReturn.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(propertyBody).isNotNull();


    }
}
