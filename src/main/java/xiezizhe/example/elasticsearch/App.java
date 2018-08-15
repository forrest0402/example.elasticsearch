package xiezizhe.example.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import xiezizhe.example.elasticsearch.config.AppConfig;
import xiezizhe.example.elasticsearch.operation.DeleteOp;
import xiezizhe.example.elasticsearch.operation.InsertionOp;
import xiezizhe.example.elasticsearch.operation.SearchOp;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author xiezizhe
 * @Description
 * @date 2018/8/15
 */
@Service
public class App {

    private static Logger logger = LoggerFactory.getLogger(App.class);

    @Autowired
    private TransportClient client;

    @Autowired
    InsertionOp insertionOp;

    @Autowired
    DeleteOp deleteOp;

    @Autowired
    SearchOp searchOp;

    @PostConstruct
    private void run() {
        try {
            final String index = "faq";
            final String type = "all_question";
            deleteOp.testDeleteIndex(index);
            JSONObject source = new JSONObject();
            source.put("properties", new JSONObject().put("question", new JSONObject().put("type", "text")));
            logger.info("{}", source);
            insertionOp.testAddMapping(index, type, source);
            insertionOp.testBulkInsert(index, type, Arrays.asList(new HashMap<String, Object>() {{
                put("question", "身份证是我的吗");
                put("store", true);
            }}, new HashMap<String, Object>() {{
                put("question", "身份证不是我的吗");
            }}, new HashMap<String, Object>() {{
                put("question", "身份证我去你妈的了");
            }}));

            client.admin().indices().prepareRefresh().get();
            searchOp.testSearchIndex(new String[]{index}, new String[]{type});
            logger.info("Hello, world");
        } catch (Exception e) {
            logger.error("{}", e);
        }
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
