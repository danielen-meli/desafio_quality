package com.meli.desafioquality.exception;

public class DistrictException extends RuntimeException{

    // - nome do bairro,
    //- valor do metro quadrado no bairro
    public DistrictException(String message) {
        super(message);
    }
}
