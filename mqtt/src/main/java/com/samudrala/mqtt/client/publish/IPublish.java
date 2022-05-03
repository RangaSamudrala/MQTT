package com.samudrala.mqtt.client.publish;

import java.util.concurrent.Callable;

public interface IPublish extends Callable<Void> {

    void publish(byte[] payload);
}
