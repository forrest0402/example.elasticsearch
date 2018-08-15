package xiezizhe.example.elasticsearch.operation;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xiezizhe
 * @Description
 * @date 2018/8/15
 */
@Service
public class InsertionOp {

    private static Logger logger = LoggerFactory.getLogger(InsertionOp.class);

    @Autowired
    private TransportClient client;

    public void testSingleInsert(String indexName, String type, Map<String, String> valueMap) {
        IndexResponse response = client.prepareIndex(indexName, type)
                .setSource(valueMap).get();
    }

    public void testBulkInsert(String indexName, String type, List<Map<String, Object>> values) {
        BulkRequestBuilder builder = client.prepareBulk();
        for (Map<String, Object> value : values) {
            builder.add(client.prepareIndex(indexName, type).setSource(value));
        }
        builder.get();
    }

    public void testAddMapping(String index, String type,JSONObject source) {

        client.admin().indices().preparePutMapping("twitter")
                .setType("user")
                .setSource(source)
                .get();
    }

}
