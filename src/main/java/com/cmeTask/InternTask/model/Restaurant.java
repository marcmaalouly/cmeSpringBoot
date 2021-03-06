package com.cmeTask.InternTask.model;

import java.util.UUID;

public class Restaurant {
    private final UUID id;
    private final String name;
    private final String address;
    private final int avgcost;
    private final int phonenumber;
    private final String type_id;

    public Restaurant(UUID id, String name, String address, int avgcost, int phonenumber,String type_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avgcost = avgcost;
        this.phonenumber = phonenumber;
        this.type_id = type_id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAvgcost() {
        return avgcost;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public String getTypeId() {
        return type_id;
    }
}
