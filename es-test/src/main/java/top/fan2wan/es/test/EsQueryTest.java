package top.fan2wan.es.test;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

import java.io.IOException;

/**
 * @Author: fanT
 * @Date: 2021/9/1 13:44
 * @Description:
 */
public class EsQueryTest {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = null;
        try {
            client = EsTest.getEsClient();
            conditionQuery(client);
        } finally {
            client.close();
        }
    }

    public static void conditionQuery(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();

        SearchSourceBuilder builder = new SearchSourceBuilder();
        // termQuery 词条匹配搜索 需要注意 词条支不支持分词
        /*builder.query(QueryBuilders.termQuery("age",55));
        builder.query(QueryBuilders.termQuery("name","b"));*/

        // 排序
        /*builder.sort("age", SortOrder.ASC)
                // 分页
//                .from(0)
                // from的值 应该是需要页码-1 * 分页条数
                .from(2)
                .size(2)
                // 过滤返回值
                .fetchSource(null,"age")
        ;*/

        // BoolQueryBuilder must 表示条件必须满足  .should(list) 表示list中的条件满足一个即可
        // must(条件1).should(条件2)  表示 在条件1 满足的情况下 还需满足条件2  而不是条件1 or 条件2

        /*BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        List<QueryBuilder> list = new ArrayList<>();
        list.add(QueryBuilders.termQuery("age",55));
        list.add(QueryBuilders.termQuery("age",25));
        list.add(QueryBuilders.termQuery("age",15));
        boolQueryBuilder.must(QueryBuilders.termQuery("age",15))
                .should().addAll(list);

        builder.query(boolQueryBuilder);*/

        // 范围查询
        /*RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("age");
        rangeQueryBuilder.gt(25);
        rangeQueryBuilder.lte(55);
        builder.query(rangeQueryBuilder);*/

        // 模糊查询 fuzziness 指定偏差的字符个数
//        builder.query(QueryBuilders.fuzzyQuery("name", "bulk").fuzziness(Fuzziness.ONE));

        // 高亮查询
        /**
         *   "highlight" : {
         *     "name" : [
         *       "<font color= 'red'>bulk1</font>"
         *     ]
         */
//        builder.query(QueryBuilders.termQuery("name", "bulk1"))
//                .highlighter(new HighlightBuilder()
//                        .preTags("<font color= 'red'>")
//                        .postTags("</font>")
//                        .field("name"));


        // 聚合查询
//        builder.aggregation(AggregationBuilders.max("maxAge").field("age"));

        // 分组查询
        builder.aggregation(AggregationBuilders.terms("ageGroup").field("age"));

        request.indices(EsTest.index)
                .source(builder);
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);

        System.out.println("==========================");
        System.out.println(searchResponse);
        System.out.println("==========================");
        System.out.println(searchResponse.getTook());
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }

    }

    public static void matchAll(RestHighLevelClient client) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(EsTest.index)
                .source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        System.out.println("==========================");
        System.out.println(searchResponse.getTook());
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }
}
