package com.qa.geocode.client;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UrlTest {

    private static final String GEO_URL = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String ADDRESS = "1600+Amphitheatre+Parkway,+Mountain+View,+CA";
    private static final String ENCODED_ADDRESS = "address=1600%2BAmphitheatre%2BParkway,%2BMountain%2BView,%2BCA";

    private GeoCodeUrl geoCodeUrl;

    @Test
    public void geoUrlTest() {
        geoCodeUrl = new GeoCodeUrl(GEO_URL);

        assertThat(geoCodeUrl.toString(), is(GEO_URL));

        geoCodeUrl.address = ADDRESS;
        assertThat(geoCodeUrl.toString(), is(GEO_URL + "?" + ENCODED_ADDRESS));
    }
}
