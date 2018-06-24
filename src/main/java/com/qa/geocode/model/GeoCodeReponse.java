package com.qa.geocode.model;

import com.google.api.client.util.Key;

public class GeoCodeReponse {

    @Key("results")
    private Result[] results;

    @Key("status")
    private String Status;

    public Result[] getResults() { return results; }

    public String getStatus() { return Status; }
}
