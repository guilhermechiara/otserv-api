package com.otserv.api.data.xml.vocation;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.otserv.api.data.entities.VocationEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "vocation")
public class VocationXml {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private int id;

    @JacksonXmlProperty(isAttribute = true, localName = "clientid")
    private int clientId;

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    public static VocationEntity to(VocationXml vocation) {
        return VocationEntity
                .builder()
                .id(vocation.getId())
                .name(vocation.getName())
                .build();
    }
}
