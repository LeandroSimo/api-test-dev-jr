package com.dev.apitestdevjr.person.PersonException;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PersonException extends RuntimeException{
    private final HttpStatus httpStatus;

    public PersonException(final String mensagem, HttpStatus httpStatus) {
        super((mensagem));
        this.httpStatus = httpStatus;
    }
}
