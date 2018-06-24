package com.qa.geocode.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.FileUtils;

public class TestUtils {

    public static String getFileContent(String filePath) throws Exception {
        return FileUtils.readFileToString(new File(generateUri(filePath)));
    }

    public static InputStream openAsInputStream(String filePath) throws Exception {
        return FileUtils.openInputStream(new File(generateUri(filePath)));
    }

    private static URI generateUri(String filePath) throws Exception {
        return TestUtils.class.getResource(filePath).toURI();
    }
}
