package com.qa.geocode.model;

import java.math.BigDecimal;

import com.google.api.client.util.Key;

public class Location {

    @Key("lat")
    private BigDecimal latitude;

    @Key("lng")
    private BigDecimal longitude;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
