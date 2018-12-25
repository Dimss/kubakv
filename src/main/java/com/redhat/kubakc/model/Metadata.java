package com.redhat.kubakc.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash(value = "metadata", timeToLive = 3)
public class Metadata implements Serializable {

    private String id;
    private String hostname;
    private String serverPort;
    private String kafkaStateImage;

    public Metadata(String id, String hostname, String serverPort, String kafkaStateImage) {
        this.hostname = hostname;
        this.id = id;
        this.serverPort = serverPort;
        this.kafkaStateImage = kafkaStateImage;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getKafkaStateImage() {
        return kafkaStateImage;
    }

    public void setKafkaStateImage(String kafkaStateImage) {
        this.kafkaStateImage = kafkaStateImage;
    }
}
