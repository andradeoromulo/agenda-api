package com.example.agendaapi.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<MensagemErro>> handle(MethodArgumentNotValidException exception) {

        List<MensagemErro> listaMensagens = new ArrayList<>();
        List<FieldError> camposInvalidos = exception.getBindingResult().getFieldErrors();

        camposInvalidos.forEach(campoInvalido -> {
            String mensagem = messageSource.getMessage(campoInvalido, LocaleContextHolder.getLocale());
            MensagemErro mensagemErro = new MensagemErro(campoInvalido.getField(), mensagem);
            listaMensagens.add(mensagemErro);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaMensagens);
    }

}
