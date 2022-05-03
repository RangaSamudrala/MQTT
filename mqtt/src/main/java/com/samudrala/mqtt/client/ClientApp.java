package com.samudrala.mqtt.client;

import com.samudrala.mqtt.client.SyncClient;
import com.samudrala.mqtt.config.ConfigOptions;
import com.samudrala.mqtt.exceptions.ClientException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * Hello world!
 *
 */
@Slf4j
public class ClientApp
{

    private final ClientRole clientRole;
    private final String target;
    private final String optionsFile;

    public ClientApp(@NonNull final String target, @NonNull final ClientRole clientRole, @NonNull final String optionsFile) {
        this.target = target;
        this.clientRole = clientRole;
        this.optionsFile = optionsFile;
    }

    public void init() throws ClientException {
        ConfigOptions options = new ConfigOptions(this.optionsFile);

        switch(clientRole){
            case SUBSCRIBE:
                break;
            default: // publish
                String id = UUID.randomUUID().toString();
                SyncClient client = new SyncClient(id, this.target);
                client.init(options);
                break;
        }
    }

    public static void main( String[] args ) {

        final String target = "tcp://localhost:1883";
        try {
            ClientApp app = new ClientApp(target, ClientRole.PUBLISH, "default-client-config.properties");
            app.init();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
