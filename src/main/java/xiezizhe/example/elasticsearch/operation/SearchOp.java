package xiezizhe.example.elasticsearch.operation;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiezizhe
 * @Description
 * @date 2018/8/15
 */
@Service
public class SearchOp {

    private static Logger logger = LoggerFactory.getLogger(SearchOp.class);

    @Autowired
    private TransportClient client;

    public void testSearchIndex(String[] indexNames, String[] types) {
        SearchResponse response = client.prepareSearch(indexNames)
                .setTypes(types)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchAllQuery()).storedFields("question")
                .setSize(60)
                .get();
        SearchHits hits = response.getHits();
        logger.info("get {} hits", hits.totalHits);
        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
    }
}
