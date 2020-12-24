package com.cmeTask.InternTask.model;

import java.util.UUID;

public class Type {
    private final UUID id;
    private final String type;

    public Type(UUID id, String type) {
        this.id = id;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
