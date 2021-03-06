package cn.yt4j.gateway.config;

import cn.yt4j.core.constant.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author gyv12345@163.com
 * @date 2021/4/14
 */
@Slf4j
@Configuration
public class RedisListenerConfig {

    /**
     * redis 监听配置
     * @param redisConnectionFactory redis 配置
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener((message, bytes) -> {
            log.warn("监听到redis");
            String body=String.valueOf( message.getBody());
            String bytess=String.valueOf(bytes);
            log.info("body:[{}]", body);
            log.info("bytes:[{}]", bytess);
        }, new ChannelTopic(RedisConstants.MESSAGE_TOPIC));
        return container;
    }
}
