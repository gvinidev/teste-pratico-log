package com.teste.pratico.backend.config;

import com.teste.pratico.model.exception.AgendamentoExistenteException;
import com.teste.pratico.model.exception.LimiteAgendamentoException;
import com.teste.pratico.model.exception.VagasExistenteException;
import com.teste.pratico.model.exception.VagasIndisponiveisException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VagasIndisponiveisException.class)
    public ResponseEntity<String> handleVagasIndisponiveis(VagasIndisponiveisException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AgendamentoExistenteException.class)
    public ResponseEntity<String> handleAgendamentoExistente(AgendamentoExistenteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LimiteAgendamentoException.class)
    public ResponseEntity<String> handleLimiteAgendamento(LimiteAgendamentoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VagasExistenteException.class)
    public ResponseEntity<String> handleLimiteAgendamento(VagasExistenteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}