package com.github.sailboat.notes.repository.timing;




import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;



/**
 * @author zhumeng
 * @version 1.0
 * @description: TODO
 * @date 2024/6/13
 */
public final class WriteQueryExample {

    public static void main(final String[] args) throws Exception {
        String hostUrl = "http://121.199.33.29:8086";

        char[] authToken = "tjgI5_s6BZBWJO_xA8kWU08zZSJPuqzEGdBaHaurvpwaEi0D6ljA7R9wqQw42mykt3-fuufXxNYL0ApiIyWFzg==".toCharArray();


        try (InfluxDBClient client = InfluxDBClientFactory.create(hostUrl, authToken, "Legend_80s","weather")) {

            String measurementName = "temperature";

            List<Point> points = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                points.add(Point.measurement(measurementName)
                        .addTag("city", "南京")
                        .addTag("area", "江宁")
                        .addField("Temperature", ThreadLocalRandom.current().nextInt(21) + 20)
                        .addField("Humidity", ThreadLocalRandom.current().nextInt(91) + 90));
            }

//            for (Point point : points) {
            client.getWriteApiBlocking().writePoints(points);

            Thread.sleep(1000); // separate points by 1 second
//            }

            System.out.println("Complete. Return to the InfluxDB UI. - "+client.version());


            String sql = "from(bucket: \"weather\")\n" +
                    "    |> range(start:-1h)";

            List<FluxTable> stream = client.getQueryApi().query(sql, "Legend_80s");
            System.out.println(stream.size());

        }
    }
}