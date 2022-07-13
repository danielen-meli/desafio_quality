package com.meli.desafioquality.exception;

public class PropertyException  extends RuntimeException {
// - um nome do imóvel,
//- um bairro,
//- e a lista de cômodos.


    public PropertyException(String message) {
        super(message);
    }
}
