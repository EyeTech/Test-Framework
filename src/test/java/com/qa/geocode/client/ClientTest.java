package com.qa.geocode.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.qa.geocode.model.Result;
import com.qa.geocode.utils.TestUtils;

public class ClientTest {

    private static final String GEO_CODE_RESULT = "/geo-code-result.json";
    private static final String ADDRESS = "1600+Amphitheatre+Parkway,+Mountain+View,+CA";
    private static final int PORT = 12111;
    private static final String GEOCODE_HOST = "http://localhost:" + PORT;
    private static final String GEOCODE_URL = "/maps/api/geocode/json";
    private static final String URL_TO_MATCH = GEOCODE_URL + "\\?address=1600%2BAmphitheatre%2BParkway,%2BMountain%2BView,%2BCA";

    private static final String EXPECTED_LATITUDE = "37.4224082";
    private static final String EXPECTED_LONGITUDE = "-122.0856086";
    private static final String EXPECTED_ADDRESS = "Google Building 41, 1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(PORT);

    @Test
    public void clientTest() throws Exception {

        wireMockRule.stubFor(get(urlMatching(URL_TO_MATCH))
                .willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(TestUtils.getFileContent(GEO_CODE_RESULT))));


        GeoCodeHttpClientImpl client = new GeoCodeHttpClientImpl(GEOCODE_HOST + GEOCODE_URL, ADDRESS);
        Result[] results = client.getGeoCode().getResults();

        assertThat(results[0].getGeometry().getLocation().getLatitude().toString(), is(EXPECTED_LATITUDE));
        assertThat(results[0].getGeometry().getLocation().getLongitude().toString(), is(EXPECTED_LONGITUDE));
        assertThat(results[0].getAddress(), is(EXPECTED_ADDRESS));

    }
}
