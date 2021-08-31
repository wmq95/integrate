package top.fan2wan.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author: fanT
 * @Date: 2021/8/31 9:33
 * @Description: test for es
 */
public class EsTest {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.99.100", 9200, "http"))
        );
        esClient.ping(RequestOptions.DEFAULT);
        esClient.close();
    }
}
