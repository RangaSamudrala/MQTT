package com.samudrala.mqtt.config;

import com.samudrala.mqtt.exceptions.ClientConfigException;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Data
public class ConfigOptions {


    private MqttConnectOptions connectOptions;

    public ConfigOptions() {
        this.connectOptions = new MqttConnectOptions();
    }

    public ConfigOptions(@NonNull final String optionsFile) throws ClientConfigException {

        this.connectOptions = new MqttConnectOptions();

        if (!Files.exists(Paths.get(optionsFile))) {
            throw new ClientConfigException(String.format("File not found: %s", optionsFile));
        }

        Configurations configs = new Configurations();
        try{
            Configuration config = configs.properties(new File(optionsFile));
            this.withPassword(config.getString("password", "").toCharArray())
                    .withUserName(config.getString("user.name", ""))
                    .withMaxReconnectDelay(config.getInt("max.reconnect.delay", 10))
                    .withKeepAliveInterval(config.getInt("keep.alive.interval", 60))
                    .withMaxInflight(config.getInt("max.in.flight", 100))
                    .withConnectionTimeout(config.getInt("connection.timeout", 60))
                    .withHttpsHostnameVerificationEnabled(config.getBoolean("hostname.verification.enabled", false))
                    .withServerURIs(config.getStringArray("server.uris"), new String[]{})
                    .withMqttVersion(config.getInt("mqtt.version", -1))
                    .withAutomaticReconnect(config.getBoolean("max.automatic.reconnect", true))
                    .withExecutorServiceTimeout(config.getInt("executor.service.timeout", 10))
                    .withCleanSession(config.getBoolean("clean.session", true));

            //.withWill(config.getString("will", "").getBytes(StandardCharsets.UTF_8), )
            //.withSocketFactory(config.getString("socket.port", ""))
            //.withWillMessage(config.getString("will.message", ""))
            //.withHostnameVerifier(config.getString("host.name.verifier", ""))
            //.withServer(config.getString("host.name.verifier", ""))
            //.withCustomWebSocketHeaders

        } catch (ConfigurationException e) {
            throw new ClientConfigException(e);
        }

//        Properties properties = new Properties();
//        try {
//            properties.load(new FileReader(new File(optionsFile)));
//            Iterator<Map.Entry<Object, Object>> itr = properties.entrySet().iterator();
//            while(itr.hasNext()) {
//                Map.Entry<Object, Object> entry = itr.next();
//
//                if(entry.getKey())
//            }
//        } catch (IOException e) {
//            throw new ClientConfigException(e);
//        }

    }
    public char[] getPassword() {
        return connectOptions.getPassword();
    }

    public ConfigOptions withPassword(char[] password) {
        connectOptions.setPassword(password);
        return this;
    }

    public String getUserName() {
        return connectOptions.getUserName();
    }

    public ConfigOptions withUserName(String userName) {
        connectOptions.setUserName(userName);
        return this;
    }

    public int getMaxReconnectDelay() {
        return connectOptions.getMaxReconnectDelay();
    }

    public ConfigOptions withMaxReconnectDelay(int maxReconnectDelay) {
        connectOptions.setMaxReconnectDelay(maxReconnectDelay);
        return this;
    }

    public ConfigOptions withWill(MqttTopic topic, byte[] payload, int qos, boolean retained) {
        connectOptions.setWill(topic, payload, qos, retained);
        return this;
    }

    public ConfigOptions withWill(String topic, byte[] payload, int qos, boolean retained) {
        connectOptions.setWill(topic, payload, qos, retained);
        return this;
    }

    public int getKeepAliveInterval() {
        return connectOptions.getKeepAliveInterval();
    }

    public int getMqttVersion() {
        return connectOptions.getMqttVersion();
    }

    public ConfigOptions withKeepAliveInterval(int keepAliveInterval) throws IllegalArgumentException {
        connectOptions.setKeepAliveInterval(keepAliveInterval);
        return this;
    }

    public int getMaxInflight() {
        return connectOptions.getMaxInflight();
    }

    public ConfigOptions withMaxInflight(int maxInflight) {
        connectOptions.setMaxInflight(maxInflight);
        return this;
    }

    public int getConnectionTimeout() {
        return connectOptions.getConnectionTimeout();
    }

    public ConfigOptions withConnectionTimeout(int connectionTimeout) {
        connectOptions.setConnectionTimeout(connectionTimeout);
        return this;
    }

    public SocketFactory getSocketFactory() {
        return connectOptions.getSocketFactory();
    }

    public ConfigOptions withSocketFactory(SocketFactory socketFactory) {
        connectOptions.setSocketFactory(socketFactory);
        return this;
    }

    public String getWillDestination() {
        return connectOptions.getWillDestination();
    }

    public MqttMessage getWillMessage() {
        return connectOptions.getWillMessage();
    }

    public Properties getSSLProperties() {
        return connectOptions.getSSLProperties();
    }

    public ConfigOptions withSSLProperties(Properties props) {
        connectOptions.setSSLProperties(props);
        return this;
    }

    public boolean isHttpsHostnameVerificationEnabled() {
        return connectOptions.isHttpsHostnameVerificationEnabled();
    }

    public ConfigOptions withHttpsHostnameVerificationEnabled(boolean httpsHostnameVerificationEnabled) {
        connectOptions.setHttpsHostnameVerificationEnabled(httpsHostnameVerificationEnabled);
        return this;
    }

    public HostnameVerifier getSSLHostnameVerifier() {
        return connectOptions.getSSLHostnameVerifier();
    }

    public ConfigOptions withSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        connectOptions.setSSLHostnameVerifier(hostnameVerifier);
        return this;
    }

    public boolean isCleanSession() {
        return connectOptions.isCleanSession();
    }

    public ConfigOptions withCleanSession(boolean cleanSession) {
        connectOptions.setCleanSession(cleanSession);
        return this;
    }

    public String[] getServerURIs() {
        return connectOptions.getServerURIs();
    }

    public ConfigOptions withServerURIs(String[] serverURIs, String[] strings) {
        connectOptions.setServerURIs(serverURIs);
        return this;
    }

    public ConfigOptions withMqttVersion(int mqttVersion) throws IllegalArgumentException {
        connectOptions.setMqttVersion(mqttVersion);
        return this;
    }

    public boolean isAutomaticReconnect() {
        return connectOptions.isAutomaticReconnect();
    }

    public ConfigOptions withAutomaticReconnect(boolean automaticReconnect) {
        connectOptions.setAutomaticReconnect(automaticReconnect);
        return this;
    }

    public int getExecutorServiceTimeout() {
        return connectOptions.getExecutorServiceTimeout();
    }

    public ConfigOptions withExecutorServiceTimeout(int executorServiceTimeout) {
        connectOptions.setExecutorServiceTimeout(executorServiceTimeout);
        return this;
    }

    public Properties getDebug() {
        return connectOptions.getDebug();
    }

    public ConfigOptions withCustomWebSocketHeaders(Properties props) {
        connectOptions.setCustomWebSocketHeaders(props);
        return this;
    }

    public Properties getCustomWebSocketHeaders() {
        return connectOptions.getCustomWebSocketHeaders();
    }

}
