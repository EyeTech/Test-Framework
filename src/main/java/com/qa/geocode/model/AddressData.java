package com.qa.geocode.model;

import java.math.BigDecimal;

public class AddressData {

    private final String address;
    private final BigDecimal latitude;
    private final BigDecimal longitude;

    public AddressData(String address, BigDecimal latitude, BigDecimal longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() { return address; }

    public BigDecimal getLatitude() { return latitude; }

    public BigDecimal getLongitude() { return longitude; }
}
