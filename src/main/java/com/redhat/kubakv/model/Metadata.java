package com.redhat.kubakv.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "metadata")
public class Metadata implements Serializable {

    private String id;
    private String hostname;

    public Metadata(String id, String hostname) {
        this.hostname = hostname;
        this.id = id;
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
}

