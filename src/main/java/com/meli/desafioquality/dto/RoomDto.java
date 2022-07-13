package com.meli.desafioquality.dto;

import com.meli.desafioquality.model.Room;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter
public class RoomDto {
    @NotEmpty(message = "O nome do cômodo não pode estar vazio")
    @Length(min = 1, max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres")
    private String name;

    private double area;

    public RoomDto(Room room) {
        this.name = room.getName();
        this.area = room.getLength() * room.getWidth();
    }
}
