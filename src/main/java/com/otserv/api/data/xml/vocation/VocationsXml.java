package com.otserv.api.data.xml.vocation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.otserv.api.config.FoldersConfiguration;
import com.otserv.api.data.entities.VocationEntity;
import com.otserv.api.data.xml.XmlHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "vocations")
public class VocationsXml {
    private static Logger logger = LoggerFactory.getLogger(VocationXml.class);

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "vocation")
    List<VocationXml> vocations;

    public static VocationsXml read(FoldersConfiguration pathConfiguration) throws IOException {
        return XmlHandler.read(
                new File(String.format("%s/XML/vocations.xml", pathConfiguration.getDataPath())),
                VocationsXml.class
        );
    }

    public static List<VocationEntity> to(VocationsXml vocations) {
        return vocations
                .getVocations()
                .stream()
                .map(VocationXml::to)
                .collect(Collectors.toList());
    }
}
