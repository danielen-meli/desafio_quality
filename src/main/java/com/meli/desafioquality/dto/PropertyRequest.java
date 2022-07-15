package com.meli.desafioquality.dto;

import com.meli.desafioquality.model.District;
import com.meli.desafioquality.model.Room;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class PropertyRequest {
    private Long id;

    @NotEmpty(message = "O nome da propriedade não pode estar vazio")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome da propriedade deve começar com letra maiúscula.")
    @Length(min = 1, max = 30, message = "O comprimento da propriedade não pode exceder 30 caracteres")
    private String name;

    @NotEmpty(message = "O nome do bairro não pode estar vazio")
    @Length (min = 1, max = 45, message = "O comprimento do bairro não pode exceder 30 caracteres")
    private String district;

    private List<@Valid Room> listRoom;
}
