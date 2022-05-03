package com.samudrala.mqtt.client;

import com.samudrala.mqtt.config.ConfigOptions;
import com.samudrala.mqtt.exceptions.ClientException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Objects;

@Slf4j
@Data
public class SyncClient {

    final String id;
    final String target;
    private IMqttClient client;

    public SyncClient(@NonNull final String id, @NonNull final String target) {
        Objects.requireNonNull(id, "id cannot be null");
        Objects.requireNonNull(target, "target cannot be null");

        this.id = id;
        this.target = target;
    }

    public void init(ConfigOptions options) throws ClientException {
        try {
            this.client = new MqttClient(this.target, this.id);
            this.client.connect(options.getConnectOptions());
        } catch (MqttException e) {
            log.error(e.getMessage(), e);
            throw new ClientException(e);
        }
    }


}
