package com.qa.geocode.client;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class GeoCodeUrl extends GenericUrl {

    @Key
    public String address;

    public GeoCodeUrl(String url) {
        super(url);
    }
}
