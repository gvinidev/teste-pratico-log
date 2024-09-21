package com.teste.pratico.model.exception;

import java.io.Serial;

public class VagasIndisponiveisException extends Exception {

    @Serial
    private static final long serialVersionUID = 8255874536488978550L;

    public VagasIndisponiveisException() {
        super("Não existem vagas disponíveis para o período");
    }
}
