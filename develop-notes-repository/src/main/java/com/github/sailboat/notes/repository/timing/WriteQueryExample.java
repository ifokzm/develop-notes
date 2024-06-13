//package com.github.sailboat.notes.repository.timing;
//
//
//
//
//import com.influxdb.client.InfluxDBClient;
//import com.influxdb.client.InfluxDBClientFactory;
//import com.influxdb.client.write.Point;
//import com.influxdb.query.FluxTable;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//
//
///**
// * @author zhumeng
// * @version 1.0
// * @description: TODO
// * @date 2024/6/13
// */
//public final class WriteQueryExample {
//
//    public static void main(final String[] args) throws Exception {
//        String hostUrl = "https://us-east-1-1.aws.cloud2.influxdata.com";
//
//        char[] authToken = "H5mOfvpPLAhVGVQg2sKpmjfKW7Q_6YjDG5ng-239iYgRdPJRNNgc0Ar-YrWpu4cjT_zq4kT7IxRhozPGdl-xQA==".toCharArray();
//
//
//        try (InfluxDBClient client = InfluxDBClientFactory.create(hostUrl, authToken, "80 Legends","wind")) {
//            String database = "wind";
//
//            Point[] points = new Point[] {
//                    Point.measurement("census")
//                            .addTag("location", "Klamath")
//                            .addField("bees", 23),
//                    Point.measurement("census")
//                            .addTag("location", "Portland")
//                            .addField("ants", 30),
//                    Point.measurement("census")
//                            .addTag("location", "Klamath")
//                            .addField("bees", 28),
//                    Point.measurement("census")
//                            .addTag("location", "Portland")
//                            .addField("ants", 32),
//                    Point.measurement("census")
//                            .addTag("location", "Klamath")
//                            .addField("bees", 29),
//                    Point.measurement("census")
//                            .addTag("location", "Portland")
//                            .addField("ants", 40)
//            };
//
////            for (Point point : points) {
////                client.getWriteApi().writePoint(point);
////
////                Thread.sleep(1000); // separate points by 1 second
////            }
//
//            System.out.println("Complete. Return to the InfluxDB UI."+client.version());
//
//
//            String sql = "SELECT * " +
//                    "FROM 'census' " +
//                    "WHERE time >= now() - interval '1 hour' " +
//                    "AND ('bees' IS NOT NULL OR 'ants' IS NOT NULL) order by time asc";
//
////
////
//            List<FluxTable> stream = client.getQueryApi().query(sql, "wind");
//            System.out.println(stream.size());
//
//        }
//    }
//}