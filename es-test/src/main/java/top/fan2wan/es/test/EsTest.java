package top.fan2wan.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.*;

import java.io.IOException;

/**
 * @Author: fanT
 * @Date: 2021/8/31 9:33
 * @Description: test for es
 */
public class EsTest {

    private static String hostname = "myEs";
    private static int port = 9200;
    private static String scheme = "http";
    public static String index = "client";

    public static void main(String[] args) throws IOException {
        RestHighLevelClient esClient = getEsClient();
        esClient.ping(RequestOptions.DEFAULT);

       /* CreateIndexResponse response = createIndex(esClient, EsTest.index);
        System.out.println("创建索引: " + response.isAcknowledged());*/

        /*System.out.println("get index...");
        GetIndexResponse index = getIndex(esClient, EsTest.index);
        System.out.println(index.getAliases());
        System.out.println(index.getMappings());
        System.out.println(index.getSettings());*/

        /*AcknowledgedResponse response = delete(esClient, index);
        System.out.println("删除索引 :" + response.isAcknowledged());*/

        getMapping(esClient);
        esClient.close();
    }

    public static RestHighLevelClient getEsClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostname, port, scheme))
        );
    }


    public static CreateIndexResponse createIndex(RestHighLevelClient client, String index) throws IOException {
        return client.indices().create(new CreateIndexRequest(index), RequestOptions.DEFAULT);
    }

    public static GetIndexResponse getIndex(RestHighLevelClient client, String index) throws IOException {
        return client.indices().get(new GetIndexRequest(index), RequestOptions.DEFAULT);
    }

    public static AcknowledgedResponse delete(RestHighLevelClient client, String index) throws IOException {
        return client.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
    }

    public static void getMapping(RestHighLevelClient client) throws IOException {
        GetMappingsResponse mappingsResponse = client.indices().getMapping(new GetMappingsRequest().indices(index), RequestOptions.DEFAULT);
        mappingsResponse.mappings().entrySet().forEach(System.out::println);
    }
}
