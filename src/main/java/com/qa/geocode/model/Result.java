package com.qa.geocode.model;

import com.google.api.client.util.Key;

public class Result {

    @Key("geometry")
    private Geometry geometry;

    @Key("formatted_address")
    private String address;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getAddress() { return address; }
}
