package top.fan2wan.es.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import top.fan2wan.es.test.entity.EsUser;

import java.io.IOException;

/**
 * @Author: fanT
 * @Date: 2021/9/1 9:19
 * @Description:
 */
public class EsDocTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = null;
        try {
            client = EsTest.getEsClient();

            /*IndexResponse response = insertDoc(client);
            System.out.println("插入 文档对象:" + response.getResult());*/

            /*UpdateResponse response = localUpdateDoc(client);
            System.out.println("更新结果:" + response);*/

//            DeleteResponse deleteResponse = deleteDoc(client);
//            System.out.println("删除 文档:" + deleteResponse);
            batchInsert(client);
        } finally {
            client.close();
        }
    }

    /**
     * 新增对象
     *
     * @return IndexResponse
     */
    public static IndexResponse insertDoc(RestHighLevelClient client) throws IOException {
        IndexRequest indexRequest = new IndexRequest();
        EsUser user = new EsUser("fantao", "男", 29);
        String userJsonStr = objectMapper.writeValueAsString(user);
        // 设置es id
        indexRequest.id("1001")
                //指定索引
                .index(EsTest.index)
                // source 就表示es 的对象 注意 需要的是json 格式的
                .source(userJsonStr, XContentType.JSON);
        return client.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 局部更新 doc
     *
     * @param client
     * @return
     * @throws IOException
     */
    public static UpdateResponse localUpdateDoc(RestHighLevelClient client) throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index(EsTest.index)
                .id("1001")
                .doc(XContentType.JSON, "age", 31, "name", "fantaoAAAAA");
        return client.update(request, RequestOptions.DEFAULT);
    }

    public static DeleteResponse deleteDoc(RestHighLevelClient client) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.id("1001")
                .index(EsTest.index);
        return client.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量新增
     *
     * @param client
     * @return
     * @throws IOException
     */
    public static BulkResponse batchInsert(RestHighLevelClient client) throws IOException {
        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest().id("1100").index(EsTest.index).source(XContentType.JSON, "name", "bulk1", "age", 15, "sex", "男"));
        request.add(new IndexRequest().id("1101").index(EsTest.index).source(XContentType.JSON, "name", "bulk2", "age", 25, "sex", "女"));
        request.add(new IndexRequest().id("1102").index(EsTest.index).source(XContentType.JSON, "name", "bulk3", "age", 18, "sex", "男"));
        request.add(new IndexRequest().id("1103").index(EsTest.index).source(XContentType.JSON, "name", "bulk4", "age", 55, "sex", "男"));
        request.add(new IndexRequest().id("1104").index(EsTest.index).source(XContentType.JSON, "name", "aaaa", "age", 26, "sex", "女"));
        request.add(new IndexRequest().id("1105").index(EsTest.index).source(XContentType.JSON, "name", "bbbb", "age", 18, "sex", "女"));

        return client.bulk(request, RequestOptions.DEFAULT);
    }

    /**
     * 批量删除
     *
     * @param client
     * @return
     * @throws IOException
     */
    public static BulkResponse batchDelete(RestHighLevelClient client) throws IOException {
        BulkRequest request = new BulkRequest();

        request.add(new DeleteRequest().id("1100").index(EsTest.index));
        request.add(new DeleteRequest().id("1101").index(EsTest.index));
        request.add(new DeleteRequest().id("1102").index(EsTest.index));

        return client.bulk(request, RequestOptions.DEFAULT);
    }
}
