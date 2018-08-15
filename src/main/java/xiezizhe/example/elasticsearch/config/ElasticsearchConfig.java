package xiezizhe.example.elasticsearch.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xiezizhe
 * @Description
 * @date 2018/8/15
 */
@Configuration
public class ElasticsearchConfig {

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);

    @Bean(destroyMethod = "close")
    public TransportClient getTransportClient() throws UnknownHostException {
        return new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
//        return new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
    }
}
