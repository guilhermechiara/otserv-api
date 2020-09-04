package com.otserv.api.data.xml;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public abstract class XmlHandler {
    private static final XmlMapper mapper = new XmlMapper();

    public static <T> T read(File file, Class<T> type) throws IOException {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(file, type);
    }

    public static <T> void write(File file, Class<T> type) throws IOException {
        mapper.writeValue(file, type);
    }
}
