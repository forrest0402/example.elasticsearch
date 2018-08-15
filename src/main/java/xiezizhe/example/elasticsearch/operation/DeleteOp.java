package xiezizhe.example.elasticsearch.operation;

import org.elasticsearch.client.transport.TransportClient;
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
public class DeleteOp {

    private static Logger logger = LoggerFactory.getLogger(DeleteOp.class);

    @Autowired
    private TransportClient client;

    public void testDeleteIndex(String indexName) {
        try {
            client.admin().indices().prepareDelete(indexName).execute().actionGet();
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }
}
