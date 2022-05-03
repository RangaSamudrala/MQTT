package com.samudrala.mqtt.exceptions;

public class ClientConfigException extends ClientException {

    public ClientConfigException(Exception e) {
        super(e);
    }

    public ClientConfigException(String msg) {
        super(msg);
    }
}
