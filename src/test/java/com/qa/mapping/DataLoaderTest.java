package com.qa.mapping;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.qa.geocode.model.AddressData;

public class DataLoaderTest {

    private static final String ADDRESS_DATA = "/address-mapping.json";
    private static final String TEST_ADDRESS = "address 01";

    private Map<Integer, AddressData> addressDataMap;

    @Before
    public void setup() throws IOException {
        DataLoader dataLoader = new DataLoader(ADDRESS_DATA);
        addressDataMap = dataLoader.garnerData();
    }

    @Test
    public void shouldLoadCorrectData() {
        assertThat(addressDataMap.values(), hasSize(7));
    }

    @Test
    public void addressIsNotValidKey() {
        assertFalse(addressDataMap.containsKey(TEST_ADDRESS));
    }

    @Test
    public void addressHashShouldRetrieveData() {
        assertTrue(addressDataMap.containsKey(TEST_ADDRESS.hashCode()));

        AddressData addressData = addressDataMap.get(TEST_ADDRESS.hashCode());
        assertThat(addressData.getAddress(), is(TEST_ADDRESS));
        assertThat(addressData.getLatitude(), is(BigDecimal.valueOf(-33.77)));
        assertThat(addressData.getLongitude(), is(BigDecimal.valueOf(-22.92)));
    }

}
