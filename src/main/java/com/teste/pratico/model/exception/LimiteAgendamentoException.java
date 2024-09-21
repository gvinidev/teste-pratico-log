package com.teste.pratico.model.exception;

import java.io.Serial;

public class LimiteAgendamentoException extends Exception {

    @Serial
    private static final long serialVersionUID = -2819611051775094924L;

    public LimiteAgendamentoException() {
        super("Limite de agendamentos atingido para o solicitante.");
    }
}
