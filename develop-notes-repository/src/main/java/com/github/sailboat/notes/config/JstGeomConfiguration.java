package com.github.sailboat.notes.config;

import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ByteOrderValues;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;
import org.locationtech.jts.io.WKTReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

public class JstGeomConfiguration {
    private GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

//    @Bean(name = "wkbWriter")
    public WKBWriter wkbWriter() {
        return new WKBWriter(2, ByteOrderValues.LITTLE_ENDIAN);
    }

//    @Bean(name = "wkbReader")
    public WKBReader wkbReader() {
        return new WKBReader(geometryFactory);
    }

//    @Bean(name = "wktReader")
    public WKTReader wktReader() {
        return new WKTReader(geometryFactory);
    }

}
