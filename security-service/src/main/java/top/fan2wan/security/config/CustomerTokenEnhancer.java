package top.fan2wan.security.config;

import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * @Author: fanT
 * @Date: 2021/2/20 8:57
 * @Description: customerEnhancer for token
 */
public class CustomerTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        OAuth2RefreshToken refreshToken = result.getRefreshToken();
        Map<String, Object> map = Maps.newHashMap();
        map.put("refresh", refreshToken.getValue());
        result.setAdditionalInformation(map);
        return result;
    }
}
