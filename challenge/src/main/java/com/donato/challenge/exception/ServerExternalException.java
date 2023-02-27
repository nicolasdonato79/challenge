package com.donato.challenge.exception;

public class ServerExternalException extends RuntimeException{


    public ServerExternalException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    public ServerExternalException(String mensaje) {
        super(mensaje);
    }


}
