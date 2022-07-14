package com.meli.desafioquality.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {
    private Long id;

    @NotEmpty(message = "O nome da propriedade não pode estar vazio")
    @Length (min = 1, max = 30, message = "O comprimento da propriedade não pode exceder 30 caracteres")
    private String name;

    @NotEmpty(message = "O nome do bairro não pode estar vazio")
    @Length (min = 1, max = 45, message = "O comprimento do bairro não pode exceder 30 caracteres")
    private District district;

    private List<@Valid Room> listRoom;
}
