package com.teste.pratico.model.exception;

import java.io.Serial;

public class VagasExistenteException extends Exception {

    @Serial
    private static final long serialVersionUID = 8255874536488978550L;

    public VagasExistenteException() {
        super("Já existe uma vaga cadastrada nesse período");
    }
}
