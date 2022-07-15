package com.meli.desafioquality.model;


import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {
    private Long id;
    private String name;
    private District district;
    private List<Room> listRoom;
}
