package com.github.sailboat.notes.service.slave;

import java.util.Date;

import javax.annotation.Resource;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.operation.linemerge.LineMerger;
import org.springframework.stereotype.Service;

import com.github.sailboat.notes.repository.slave.dao.TestDao;
import com.github.sailboat.notes.repository.slave.po.TestEntity;

@Service
public class TestService {

    @Resource
    TestDao testDao;

    public TestEntity find() {
        return testDao.getById(1);
    }

    public void save(){
        TestEntity entity = new TestEntity();
        entity.setId(9L);

        GeometryFactory geometryFactory = new GeometryFactory();

        LineString lineString = geometryFactory.createLineString(new Coordinate[]{
                new Coordinate(11.0, 11.0),
                new Coordinate(12.0, 12.0)
        });


        lineString.setUserData(new Date());

        entity.setLine(lineString);
        testDao.save(entity);
    }
}
