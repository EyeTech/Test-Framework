package com.qa.geocode.model;

import static com.qa.geocode.utils.TestUtils.openAsInputStream;
import static java.nio.charset.Charset.defaultCharset;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class ModelTest {

    private static final String ADDRESS_RESULT = "/geo-code-result.json";
    private static final String EXPECTED_LATITUDE = "37.4224082";
    private static final String EXPECTED_LONGITUDE = "-122.0856086";
    private static final String EXPECTED_ADDRESS = "Google Building 41, 1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA";

    private static Result[] results;

    @BeforeClass
    public static void BeforeClass() throws Exception {
        JsonObjectParser parser = new JsonObjectParser(new JacksonFactory());

        GeoCodeReponse response = parser.parseAndClose(openAsInputStream(ADDRESS_RESULT),
                defaultCharset(), GeoCodeReponse.class);
        results = response.getResults();
    }

    @Test
    public void shouldExtractLatLong() {
        assertThat(results[0].getGeometry().getLocation().getLatitude().toString(), is(EXPECTED_LATITUDE));
        assertThat(results[0].getGeometry().getLocation().getLongitude().toString(), is(EXPECTED_LONGITUDE));
    }

    @Test
    public void addressAndLocationShouldMatch() {
        assertThat(results[0].getAddress(), is(EXPECTED_ADDRESS));
    }
}
