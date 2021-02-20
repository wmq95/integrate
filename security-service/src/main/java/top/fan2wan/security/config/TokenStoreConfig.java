package top.fan2wan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import top.fan2wan.security.util.JwtUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/2/5 14:02
 * @Description: config for tokenStore
 * <p>
 * <p>
 * 使用jwt token store : 底层源码可以看见 对于access_token 和 refresh_token 都没进行保存
 * <p>
 * refresh_token的保存 如果直接返给前端，保存在前端或者其他地方感觉不合适，所以使用CustomerTokenConverter重写enhance方法;
 * 把refresh_token放入access_token 中。
 * TODO 实现自动过期access_token 自动续租
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
        JwtAccessTokenConverter converter = new CustomerTokenConverter();
        converter.setSigningKey(JwtUtil.SECRET);
        return converter;
    }
}

class CustomerTokenConverter extends JwtAccessTokenConverter {

    private JsonParser objectMapper = JsonParserFactory.create();

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
        String tokenId = result.getValue();
        if (!info.containsKey(TOKEN_ID)) {
            info.put(TOKEN_ID, tokenId);
        } else {
            tokenId = (String) info.get(TOKEN_ID);
        }

        // access_token 包含自动刷新过期token需要的数据(client_id/secret/refresh_token)
        Map<String, Object> details = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        if (!Objects.isNull(details) && details.size() > 0) {
            info.put("client_id",
                    details.getOrDefault("client_id", details.get("client_id")));

            info.put("client_secret",
                    details.getOrDefault("client_secret", details.get("client_secret")));
        }

        OAuth2RefreshToken refreshToken = result.getRefreshToken();
        if (refreshToken != null) {
            DefaultOAuth2AccessToken encodedRefreshToken = new DefaultOAuth2AccessToken(accessToken);
            encodedRefreshToken.setValue(refreshToken.getValue());
            // Refresh tokens do not expire unless explicitly of the right type
            encodedRefreshToken.setExpiration(null);
            try {
                Map<String, Object> claims = objectMapper
                        .parseMap(JwtHelper.decode(refreshToken.getValue()).getClaims());
                if (claims.containsKey(TOKEN_ID)) {
                    encodedRefreshToken.setValue(claims.get(TOKEN_ID).toString());
                }
            } catch (IllegalArgumentException e) {
            }
            Map<String, Object> refreshTokenInfo = new LinkedHashMap<String, Object>(
                    accessToken.getAdditionalInformation());
            refreshTokenInfo.put(TOKEN_ID, encodedRefreshToken.getValue());
            // refresh token包含client id/secret, 自动刷新过期token时用到。
            if (!Objects.isNull(details) && details.size() > 0) {
                refreshTokenInfo.put("client_id",
                        details.getOrDefault("client_id", details.get("client_id")));

                refreshTokenInfo.put("client_secret",
                        details.getOrDefault("client_secret", details.get("client_secret")));
            }
            refreshTokenInfo.put(ACCESS_TOKEN_ID, tokenId);
            encodedRefreshToken.setAdditionalInformation(refreshTokenInfo);
            DefaultOAuth2RefreshToken token = new DefaultOAuth2RefreshToken(
                    encode(encodedRefreshToken, authentication));
            if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
                Date expiration = ((ExpiringOAuth2RefreshToken) refreshToken).getExpiration();
                encodedRefreshToken.setExpiration(expiration);
                token = new DefaultExpiringOAuth2RefreshToken(encode(encodedRefreshToken, authentication), expiration);
            }
            result.setRefreshToken(token);
            info.put("refresh", token.getValue());
        }
        result.setAdditionalInformation(info);
        result.setValue(encode(result, authentication));
        return result;
    }
}
