package com.qa.common;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class CommonElementsTimedTest {

    private static final String[] repeatedElementsArray = { "One", "Two", "Three", "Four", "Five", "Six",
                                                            "Two", "Four", "Six", "Eight", "Ten", "Twelve"};

    private static final String[] commons = {"Two", "Four", "Six"};

    private static final int NUMBER_OFF_LOOPS = 10000;

    private CommonElements commonElements;

    @Before
    public void setup() { commonElements = new CommonElements(); }

    @Test
    public void timeDeDupedListMethod() {
        String[] arrayOne = createLargeStringArray(repeatedElementsArray, NUMBER_OFF_LOOPS);
        assertThat(arrayOne.length, is(repeatedElementsArray.length * 2 * NUMBER_OFF_LOOPS));

        String[] arrayTwo = createLargeStringArray(commons, NUMBER_OFF_LOOPS);
        assertThat(arrayTwo.length, is(commons.length * 2 * NUMBER_OFF_LOOPS));

        long startTime = System.currentTimeMillis();
        ArrayList<String> results = commonElements.getDeDupedList(
                new ArrayList<>(Arrays.asList(arrayOne)),
                new ArrayList<>(Arrays.asList(arrayTwo)));
        System.out.println(String.format("DeDuped List: Time to process %d ms", System.currentTimeMillis() - startTime));

        assertNotNull(results);
        assertThat(results, hasSize(120000));
    }

    @Test
    public void timeDeDupedListDistinctAfterMethod() {
        String[] arrayOne = createLargeStringArray(repeatedElementsArray, NUMBER_OFF_LOOPS);
        assertThat(arrayOne.length, is(repeatedElementsArray.length * 2 * NUMBER_OFF_LOOPS));

        String[] arrayTwo = createLargeStringArray(commons, NUMBER_OFF_LOOPS);
        assertThat(arrayTwo.length, is(commons.length * 2 * NUMBER_OFF_LOOPS));

        long startTime = System.currentTimeMillis();
        ArrayList<String> results = commonElements.getDeDupedListDistinctAfter(
                new ArrayList<>(Arrays.asList(arrayOne)),
                new ArrayList<>(Arrays.asList(arrayTwo)));
        System.out.println(String.format("Distinct DeDuped List After: Time to process %d ms", System.currentTimeMillis() - startTime));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Two", "Four", "Six"));
    }

    @Test
    public void timeDeDupedListDistinctBeforeMethod() {
        String[] arrayOne = createLargeStringArray(repeatedElementsArray, NUMBER_OFF_LOOPS);
        assertThat(arrayOne.length, is(repeatedElementsArray.length * 2 * NUMBER_OFF_LOOPS));

        String[] arrayTwo = createLargeStringArray(commons, NUMBER_OFF_LOOPS);
        assertThat(arrayTwo.length, is(commons.length * 2 * NUMBER_OFF_LOOPS));

        long startTime = System.currentTimeMillis();
        ArrayList<String> results = commonElements.getDeDupedListDistinctBefore(
                new ArrayList<>(Arrays.asList(arrayOne)),
                new ArrayList<>(Arrays.asList(arrayTwo)));
        System.out.println(String.format("Distinct DeDuped List Before: Time to process %d ms", System.currentTimeMillis() - startTime));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Two", "Four", "Six"));
    }

    @Test
    public void timeDeDupedHashSetMethod() {
        String[] arrayOne = createLargeStringArray(repeatedElementsArray, NUMBER_OFF_LOOPS);
        assertThat(arrayOne.length, is(repeatedElementsArray.length * 2 * NUMBER_OFF_LOOPS));

        String[] arrayTwo = createLargeStringArray(commons, NUMBER_OFF_LOOPS);
        assertThat(arrayTwo.length, is(commons.length * 2 * NUMBER_OFF_LOOPS));

        long startTime = System.currentTimeMillis();
        ArrayList<String> results = commonElements.getDeDupedHashSet(
                new ArrayList<>(Arrays.asList(arrayOne)),
                new ArrayList<>(Arrays.asList(arrayTwo)));
        System.out.println(String.format("DeDuped HashSet: Time to process %d ms", System.currentTimeMillis() - startTime));

        assertNotNull(results);
        assertThat(results, hasSize(3));
        assertThat(results, containsInAnyOrder("Two", "Four", "Six"));
    }

    private String[] createLargeStringArray(String[] seed, int n) {
        String[] joined = {};
        for (int i = 0; i < n; i++ ) {
            joined = addArrays(joined, doubleArraySize(seed));
        }
        return joined;
    }

    private String[] doubleArraySize(String[] array) {
        return Stream.concat(Arrays.stream(array), Arrays.stream(array)).toArray(String[]::new);
    }

    private String[] addArrays(String[] a, String[] b) {
        return Stream.concat(Arrays.stream(a), Arrays.stream(b)).toArray(String[]::new);
    }
}
