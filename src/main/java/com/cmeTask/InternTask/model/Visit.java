package com.cmeTask.InternTask.model;

import java.util.Date;
import java.util.UUID;

public class Visit {
    private final UUID id;
    private final String person_id;
    private final String restaurant_id;
    private final Date date;

    public Visit(UUID id, String person_id, String restaurant_id, Date date) {
        this.id = id;
        this.person_id = person_id;
        this.restaurant_id = restaurant_id;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public Date getDate() {
        return date;
    }
}
