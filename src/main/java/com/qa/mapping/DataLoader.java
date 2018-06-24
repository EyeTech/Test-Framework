package com.qa.mapping;

import static com.google.common.base.Charsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.geocode.model.AddressData;

public class DataLoader {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final String dataFile;

    public DataLoader(String filePath) { this.dataFile = filePath; }

    public Map<Integer, AddressData> garnerData() throws IOException {
        URL url = generateUrl(dataFile);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), UTF_8))) {
             return bufferedReader
                        .lines()
                        .map(DataLoader::parseLine)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .filter(DataLoader::filter)
                        .collect(Collectors.toMap(
                                (Record record) -> record.address.hashCode(),
                                (Record record) -> new AddressData(record.address, record.latitude, record.longitude)));
        }
    }

    private URL generateUrl(String filePath) { return DataLoader.class.getResource(filePath); }

    private static Optional<Record> parseLine(String line) {
        try {
            return Optional.of(mapper.readValue(line, Record.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static boolean filter(Record record) {
        return !isEmpty(
                record.address)
                && !isEmpty(record.latitude.toString())
                && !isEmpty(record.longitude.toString());
    }

    private static class Record {
        public String address;
        public BigDecimal latitude;
        public BigDecimal longitude;
    }
}
