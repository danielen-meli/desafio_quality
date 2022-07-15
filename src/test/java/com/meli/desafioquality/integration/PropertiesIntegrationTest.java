package com.meli.desafioquality.integration;

import com.meli.desafioquality.model.District;
import com.meli.desafioquality.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertiesIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
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
}
