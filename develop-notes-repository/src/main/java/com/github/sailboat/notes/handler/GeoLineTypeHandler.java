package com.github.sailboat.notes.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ByteOrderValues;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MappedTypes(value = LineString.class)
public class GeoLineTypeHandler extends BaseTypeHandler<LineString> {
    private WKBWriter wkbWriter = new WKBWriter(2, ByteOrderValues.LITTLE_ENDIAN);

    private WKBReader wkbReader = new WKBReader(new GeometryFactory(new PrecisionModel(), 4326));

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LineString lineString,
        JdbcType jdbcType) throws SQLException {
        try {
            preparedStatement.setBytes(i, convertToBytes(lineString));
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
        }
    }

    @Override
    public LineString getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return convertToGeo(resultSet.getBytes(s));
    }

    @Override
    public LineString getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return convertToGeo(resultSet.getBytes(i));
    }

    @Override
    public LineString getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return convertToGeo(callableStatement.getBytes(i));
    }

    /**
     * bytes转Geo对象
     * 
     * @param bytes
     * @return
     */
    private LineString convertToGeo(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            byte[] geomBytes = ByteBuffer.allocate(bytes.length - 4).order(ByteOrder.LITTLE_ENDIAN)
                .put(bytes, 4, bytes.length - 4).array();
            Geometry geometry = wkbReader.read(geomBytes);
            return (LineString)geometry;
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
        }
        return null;
    }

    /**
     * geo转bytes
     * 
     * @param geometry
     * @return
     * @throws IOException
     */
    private byte[] convertToBytes(Geometry geometry) throws IOException {
        byte[] geometryBytes = wkbWriter.write(geometry);
        byte[] wkb = new byte[geometryBytes.length + 4];
        // 设置SRID为4326
        ByteOrderValues.putInt(4326, wkb, ByteOrderValues.LITTLE_ENDIAN);
        System.arraycopy(geometryBytes, 0, wkb, 4, geometryBytes.length);
        return wkb;
    }

}
