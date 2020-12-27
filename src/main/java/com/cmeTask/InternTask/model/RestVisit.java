package com.cmeTask.InternTask.model;

import java.util.Date;

public class RestVisit {

    private final String RestName;
    private final Date date;

    public RestVisit(String restName, Date date) {
        RestName = restName;
        this.date = date;
    }

    public String getRestName() {
        return RestName;
    }

    public Date getDate() {
        return date;
    }
}
