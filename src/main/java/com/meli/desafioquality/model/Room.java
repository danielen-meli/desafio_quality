package com.meli.desafioquality.model;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter
public class Room {
    @NotEmpty(message = "O nome do cômodo não pode estar vazio")
    @Length(min = 1, max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres")
    private String name;

    @NotEmpty(message = "O campo não pode estar vazio")
    @Max(25)
    private double width;

    @NotEmpty(message = "O campo não pode estar vazio")
    @Max(33)
    private double length;

    private double area = width * length;

}
