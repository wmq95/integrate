package top.fan2wan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import top.fan2wan.security.util.JwtUtil;

/**
 * @Author: fanT
 * @Date: 2021/2/5 14:02
 * @Description: config for tokenStore
 */
@Configuration
public class TokenStoreConfig {

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    //使用同一个密钥来编码 JWT 中的  OAuth2 令牌
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JwtUtil.SECRET);
        return converter;
    }
}
