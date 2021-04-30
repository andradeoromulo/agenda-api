package com.example.agendaapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfOuEmailEmUsoException extends RuntimeException {

    public CpfOuEmailEmUsoException(String message) {
        super(message);
    }
}
