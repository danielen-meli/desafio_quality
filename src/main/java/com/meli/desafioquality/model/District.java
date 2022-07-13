package com.meli.desafioquality.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class District {

    private Long id;

    @NotEmpty(message = "Insira o nome do bairro")
    private String districtName;


    @NotEmpty(message = "O valor do metro quadrado no bairro não pode estar vazio")
    private double squareMeterValue;

}
