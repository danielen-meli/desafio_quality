package com.meli.desafioquality.dto;

import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Room;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class PropertyRequest {
    private Long id;

    @NotEmpty(message = "O nome da propriedade não pode estar vazio")
    @Length(min = 1, max = 30, message = "O comprimento da propriedade não pode exceder 30 caracteres")
    private String name;

    @NotEmpty(message = "O nome do bairro não pode estar vazio")
    @Length (min = 1, max = 45, message = "O comprimento do bairro não pode exceder 30 caracteres")
    private String district;

    private List<Room> listRoom;


}
