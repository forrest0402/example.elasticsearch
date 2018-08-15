package xiezizhe.example.elasticsearch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * @author xiezizhe
 * @Description
 * @date 2018/8/15
 */
@Configuration
@ComponentScan(basePackages = {"xiezizhe.example.elasticsearch"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@PropertySource(value = {"classpath:project.properties", "classpath:jdbc.properties"})
public class AppConfig {

}
