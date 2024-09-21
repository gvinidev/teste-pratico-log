package com.teste.pratico.model.exception;

import java.io.Serial;

public class AgendamentoExistenteException extends Exception {

    @Serial
    private static final long serialVersionUID = 8255874536488978550L;

    public AgendamentoExistenteException() {
        super("Já existe um agendamento cadastrado para o solicitante");
    }
}
