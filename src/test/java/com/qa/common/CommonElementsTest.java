package com.qa.common;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class CommonElementsTest {

    private static final String[] array1 = { "One", "Two", "Three", "Four", "Five", "Six"};
    private static final String[] array2 = { "Two", "Four", "Six", "Eight", "Ten", "Twelve"};

    private static final String[] array1and2 = {"One", "Two", "Three", "Four", "Five", "Six",
                                                "Two", "Four", "Six", "Eight", "Ten", "Twelve"};

    private static final String[] commons = { "Two", "Four", "Six"};

    private CommonElements commonElements;

    @Before
    public void setup() { commonElements = new CommonElements(); }

    @Test
    public void commonElementsSameSizeTest() {
        ArrayList<String> results = commonElements.getDeDupedListDistinctAfter(
                                        new ArrayList<>(Arrays.asList(array1)),
                                        new ArrayList<>(Arrays.asList(array2)));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Six", "Two", "Four"));
    }

    @Test
    public void commonElementsDifferentSizeTest() {
        ArrayList<String> results = commonElements.getDeDupedListDistinctAfter(
                                        new ArrayList<>(Arrays.asList(array1)),
                                        new ArrayList<>(Arrays.asList(commons)));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Four", "Six", "Two"));
    }

    @Test
    public void repeatedElementsListTest() {
        ArrayList<String> results = commonElements.getDeDupedListDistinctAfter(
                new ArrayList<>(Arrays.asList(array1and2)),
                new ArrayList<>(Arrays.asList(commons)));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Two", "Four", "Six"));
    }

    @Test
    public void repeatedElementsHashTest() {
        ArrayList<String> results = commonElements.getDeDupedHashSet(
                new ArrayList<>(Arrays.asList(array1and2)),
                new ArrayList<>(Arrays.asList(commons)));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Six", "Two", "Four"));
    }
}
