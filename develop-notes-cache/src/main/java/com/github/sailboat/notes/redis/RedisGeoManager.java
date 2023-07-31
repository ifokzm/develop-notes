package com.github.sailboat.notes.redis;

import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisGeoManager {

    @Resource
    RedisTemplate redisGeoTemplate;

    private static String R_KEY = "outlets";

    public void init(List<Point> points) {
        redisGeoTemplate.boundGeoOps(R_KEY).position(points);
    }

    /**
     * 根据位置名称查询坐标信息
     */
    public List<Point> queryPosition(String... key) {
        return redisGeoTemplate.boundGeoOps(R_KEY).position(key);
    }

    /**
     * 计算两点间距离
     */
    public double calcDistance(String src, String dist, Metrics metricEnum) {
        Distance distance = redisGeoTemplate.boundGeoOps(R_KEY).distance(src, dist, metricEnum);
        return distance.getValue();
    }

    /**
     * 根据一个坐标查询附近位置
     * 这个用途很广，也是存入坐标的最主要用途。给出当前地址，查询附近多少千米或者米的地点。
     * <p>
     * 使用的方法
     * <p>
     * 只返回坐标位置的名称，坐标信息没有返回的。
     * redisTemplate.boundGeoOps(K key)
     * .radius(Circle var1);
     * <p>
     * 可以根据var2配置返回值的信息。
     * redisTemplate.boundGeoOps(K key)
     * .radius(Circle var1, GeoRadiusCommandArgs var2);
     * <p>
     * var1：设置当前经纬度坐标和需要查询附近多少距离和单位。
     * var2：设置返回值的数据。
     */

    public void queryPosition(double lon, double lat) {
        Point point = new Point(lon, lat);

        Distance distance = new Distance(100, Metrics.KILOMETERS);

        Circle circle = new Circle(point, distance);

        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending().includeCoordinates().includeDistance().limit(10);

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisGeoTemplate.boundGeoOps(R_KEY).radius(circle, args);

        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            RedisGeoCommands.GeoLocation location = result.getContent();
            // 位置名称
            location.getName();
            // 位置坐标
            location.getPoint();
            // 距离
            result.getDistance().getValue();
            result.getDistance().getUnit();
        }

    }

    /**
     * 根据地点名称查询附近位置
     * <p>
     * 这个和2.5差不多，只是上面这个是根据一个经纬度坐标查询。而这个是根据地理位置名称查询，这个名称是添加位置的时候添加的名称。注意：如果查询的这个位置名称在redis中不存在，那么是会包异常的。
     * <p>
     * 使用方法
     * redisTemplate.boundGeoOps(K key)
     * .radius(M var1, Distance var2, GeoRadiusCommandArgs var3);
     * key：集合名称
     * M：位置名称，添加位置保存的那个名称。
     * var2：设置距离和单位。
     * var3：设置返回数据
     * <p>
     * 独一无二的 上面是通过 Circle对象指定坐标和距离。而这里是拆开设置，位置名称和距离信息，其余的一点都没有改变。
     * <p>
     * 特别注意：这里的位置名称一定要存在，如果位置名称不存在就会报异常。org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR could not decode requested zset member
     */

    public void queryLocation(String locationName) {
        // 50 千米
        Distance distance = new Distance(50, Metrics.KILOMETERS);
        // 指定返回的数据,当前这个点与附近这些点的距离
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
        args.sortAscending() // 升序排序
                //                .sortDescending() // 降序排序
                .includeCoordinates() // 包含坐标信息信息
                .includeDistance() // 包含距离信息
                .limit(10); // 显示返回数量
        // 根据坐标查询，并设置返回的参数
        // 只是 .radius("位置1",distance,args); 这里传输参数和testGeoRadius() 方法不同，其余全部一样。
        // 注意：查询这个名称一定要在集合中存在。如果不存在查询就会报异常  org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: ERR could not decode requested zset member
        GeoResults<RedisGeoCommands.GeoLocation<String>> outlets = redisGeoTemplate.boundGeoOps("outlets").radius("位置2", distance, args);

        // 循环输出
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : outlets) {

            // 返回网点信息
            RedisGeoCommands.GeoLocation<String> location = result.getContent();
            // 网点名
            String name = location.getName();
            // 网点坐标
            Point point1 = location.getPoint();

            // 返回距离
            Distance dis = result.getDistance();
            // 距离
            double value = dis.getValue();
            // 单位
            String unit = dis.getUnit();

            // 输出
            System.out.println("网点名：" + name + "x坐标" + point1.getX() + "y坐标" + point1.getY() + "距离：" + value + "单位：" + unit);

        }

    }
}
