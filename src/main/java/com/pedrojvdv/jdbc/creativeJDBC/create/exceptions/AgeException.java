package com.pedrojvdv.jdbc.creativeJDBC.create.exceptions;

public class AgeException extends RuntimeException {

    public AgeException() {
        super("Ocorreu um erro ao definir idade");
    }

    public AgeException(String message) {super(message);

    }


}
