package top.fan2wan.database.influxdb.util;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.*;
import org.influxdb.dto.Point.Builder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: fanT
 * @Date: 2021/8/19 13:37
 * @Description: util for influxDb
 */
public class InfluxDBUtil {

    // 用户名
    private String username;
    // 密码
    private String password;
    // 连接地址
    private String openUrl;
    // 保留策略
    private String retentionPolicy;

    private InfluxDB influxDB;

    public InfluxDBUtil(String username, String password, String openUrl,
                        String retentionPolicy) {
        this.username = username;
        this.password = password;
        this.openUrl = openUrl;
        this.retentionPolicy = retentionPolicy == null || retentionPolicy.equals("") ? "autogen" : retentionPolicy;
        influxDbBuild();
    }

    /**
     * 测试连接是否正常
     *
     * @return true 正常
     */
    public boolean ping() {
        boolean isConnected = false;
        Pong pong;
        try {
            pong = influxDB.ping();
            if (pong != null) {
                isConnected = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    /**
     * 连接时序数据库 ，若不存在则创建
     *
     * @return InfluxDB
     * @throws RuntimeException influxDB
     */
    public InfluxDB influxDbBuild() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(openUrl, username, password);
        }
        influxDB.setRetentionPolicy(retentionPolicy);
        influxDB.setLogLevel(InfluxDB.LogLevel.NONE);
        if (!ping()) {
            throw new RuntimeException("influxDB connected error");
        }
        return influxDB;
    }


    /**
     * 查询
     *
     * @param command  查询语句
     * @param database 数据库名称
     * @return QueryResult
     */
    public QueryResult query(String command, String database) {
        return influxDB.query(new Query(command, database));
    }

    /**
     * 插入
     *
     * @param database    数据库
     * @param measurement 表
     * @param tags        标签
     * @param fields      字段
     */
    public void insert(String database, String measurement, Map<String, String> tags, Map<String, Object> fields, long time,
                       TimeUnit timeUnit) {
        Builder builder = Point.measurement(measurement);
        builder.tag(tags);
        builder.fields(fields);
        if (0 != time) {
            builder.time(time, timeUnit);
        }
        influxDB.write(database, retentionPolicy, builder.build());
    }

    /**
     * 批量写入测点
     *
     * @param batchPoints
     */
    public void batchInsert(BatchPoints batchPoints) {
        influxDB.write(batchPoints);
        // influxDB.enableGzip();
        // influxDB.enableBatch(2000,100,TimeUnit.MILLISECONDS);
        // influxDB.disableGzip();
        // influxDB.disableBatch();
    }

    /**
     * 批量写入数据
     *
     * @param database        数据库
     * @param retentionPolicy 保存策略
     * @param consistency     一致性
     * @param records         要保存的数据（调用BatchPoints.lineProtocol()可得到一条record）
     */
    public void batchInsert(final String database, final String retentionPolicy, final ConsistencyLevel consistency,
                            final List<String> records) {
        influxDB.write(database, retentionPolicy, consistency, records);
    }


    /**
     * 关闭数据库
     */
    public void close() {
        influxDB.close();
    }

    /**
     * 通过udp 协议发送point
     * 无需指定database 在influxDB配置文件中指定
     *
     * @param ip       influxDB ip
     * @param port     udp port
     * @param pointStr points
     *                 measure tag1=tagValue,tag2=tagValue..  filed1=filedValue,filed2=filedValue timestamp
     */
    public static void sendPointsWithUdp(String ip, int port, String pointStr) {
        try (DatagramSocket ds = new DatagramSocket()) {
            byte[] buf = pointStr.getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(ip), port);
            ds.send(dp);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



