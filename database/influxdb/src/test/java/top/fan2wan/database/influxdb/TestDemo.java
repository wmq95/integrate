package top.fan2wan.database.influxdb;

import org.influxdb.annotation.Column;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import top.fan2wan.database.influxdb.util.InfluxDBUtil;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: fanT
 * @Date: 2021/8/19 13:30
 * @Description: test for influxdb
 */
public class TestDemo {


    //    private static String openUrl = "http://192.168.99.100:8086";
//    private static String database = "test";
    private static String username = "fant";
    private static String password = "123456";
    private static String openUrl = "http://10.15.9.110:8087";
    private static String database = "goldwind";

    public static void main(String[] args) {

        InfluxDBUtil util = new InfluxDBUtil(username, password,
                openUrl, null);

        Map<String, String> tags = new HashMap<>(8);
        tags.put("name", "test");
        tags.put("address", "wuxi");

        Map<String, Object> fields = new HashMap<>(8);
        fields.put("moeny", 100.2);
        fields.put("age", 28.0f);
        System.out.println("-------write influxDB------");
        util.insert(database, "test", tags, fields, Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS);

//        ============batch
        Long now = Instant.now().toEpochMilli();
        BatchPoints batchPoints = BatchPoints.database("test")
                .point(Point.measurement("test")
                        .tag("name", "batch1")
                        .addField("age", 123f)
                        .time(now, TimeUnit.MILLISECONDS)
                        .build())
                .point(Point.measurement("test")
                        .tag("name", "batch2")
                        .addField("age", 223f)
                        .time(now, TimeUnit.MILLISECONDS)
                        .build())
                .build();
        util.batchInsert(batchPoints);

//        add from pojo
        util.batchInsert(BatchPoints.database("test")
                .point(Point.measurement("test")
                        .addFieldsFromPOJO(new Test("pojo", "pojoAddress", 147f, 12456f))
                        .build()
                )
                .build());
        System.out.println("---------query influxDB------");


//        test for udp
        /*StringBuilder sb = new StringBuilder();
        sb.append("cpu value=2,mem=1.2");
//                .append(Instant.now().toEpochMilli());

        try (DatagramSocket ds = new DatagramSocket()) {
            System.out.printf("sql was : %s \n", sb.toString());
            byte[] buf = sb.toString().getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("10.15.9.110"), 8089);
            ds.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        InfluxDBUtil.sendPointsWithUdp("10.15.9.110", 8089,"cpu value=2,mem=1.2");

        QueryResult result = util.query(database, "select * from cpu");
        result.getResults().forEach(System.out::println);
    }

}


class Test {
    @Column(tag = true, name = "name")
    String pojoName;

    @Column(name = "address", tag = true)
    String pojoAddress;

    @Column(name = "age")
    float age;

    @Column(name = "money")
    float money;

    public Test(String pojoName, String pojoAddress, float age, float money) {
        this.pojoName = pojoName;
        this.pojoAddress = pojoAddress;
        this.age = age;
        this.money = money;
    }
}