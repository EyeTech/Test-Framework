package com.qa.geocode.model;

import com.google.api.client.util.Key;

public class Geometry {

    @Key("location")
    private Location location;

    public Location getLocation() {
        return location;
    }

}
