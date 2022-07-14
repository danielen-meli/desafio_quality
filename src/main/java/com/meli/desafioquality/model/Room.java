package com.meli.desafioquality.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @NotEmpty(message = "O nome do cômodo não pode estar vazio")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome do cômodo deve começar com letra maiúscula.")
    @Length(min = 1, max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres")
    private String name;

    @NotNull(message = "O campo não pode estar vazio")
    @Max(25)
    private double width;

    @NotNull(message = "O campo não pode estar vazio")
    @Max(33)
    private double length;

    //private double area = width * length;

}
