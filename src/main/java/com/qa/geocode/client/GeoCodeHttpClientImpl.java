package com.qa.geocode.client;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.qa.geocode.model.GeoCodeReponse;

public class GeoCodeHttpClientImpl {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final String GEOCODE_URL = "https://maps.googleapis.com/maps/api/geocode/json";

    private String geoCodeUrl;
    private String address;

    public GeoCodeHttpClientImpl(String address) {
        this(GEOCODE_URL, address);
    }

    public GeoCodeHttpClientImpl(String geoCodeUrl, String address) {
        this.geoCodeUrl = geoCodeUrl;
        this.address = address;
    }

    public GeoCodeReponse getGeoCode() throws Exception {

        HttpRequestFactory requestFactory =
            HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
                public void initialize(HttpRequest request) {
                    request.setParser(new JsonObjectParser(JSON_FACTORY));
                }
            });

        GeoCodeUrl addressUrl = new GeoCodeUrl(geoCodeUrl);
        addressUrl.address = address;

        HttpRequest request = requestFactory.buildGetRequest(addressUrl);
        return request.execute().parseAs(GeoCodeReponse.class);
    }
}
