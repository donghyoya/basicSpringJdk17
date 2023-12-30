package com.java.basic.setup.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 문제는 Application.java에 @EnableRedisHttpSession
 어노테이션을 선언할 때 redisNamespace를 별도로 정의해주지
 않을 경우 디폴트 네임스페이스인 "spring:session"가
 application.yml에 설정한 spring.session.redis.namespace을
 덮어쓰는 특성이 있기 때문에 발생했습니다.
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "${spring.session.redis.namespace}")
//Spring session redis 활성화
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;


    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }
}