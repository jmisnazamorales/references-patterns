package com.belatrix.references.patterns.exceptions;

import lombok.Getter;

@Getter
public class TechnicalException extends Throwable{

    private String message;

    public TechnicalException(String message) {
        super(message);
        this.message = message;
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
