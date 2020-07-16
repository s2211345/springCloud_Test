package com.lwc.test.model.sys.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * 密匙
     */
//    @Value("${token.jwtSecret}")
    private String apiSecretKey = "TEST;SPRINGBOOT;PROJECT;12345679";

    /**
     * 过期时间-默认2个小时
     */
    private Long expirationTime = 7200L;

    /**
     * 默认存放token的请求头
     */
    private String requestHeader = "Authorization";

    /**
     * 默认token前缀
     */
    private String tokenPrefix = "Bearer ";
}