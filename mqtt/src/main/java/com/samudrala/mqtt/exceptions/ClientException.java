package com.samudrala.mqtt.exceptions;

public class ClientException extends Exception {

    public ClientException(Exception e) {
        super(e);
    }

    public ClientException(String msg) {
        super(msg);
    }
}
