package com.meli.desafioquality.model;

import lombok.*;

import javax.validation.constraints.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {

    private Long id;

    @NotEmpty(message = "Insira o nome do bairro")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome do distrito deve começar com letra maiúscula.")

    private String districtName;

    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio")
    private double squareMeterValue;
}
