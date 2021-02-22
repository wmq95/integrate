package top.fan2wan.security.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.client.RestTemplate;
import top.fan2wan.security.config.CustomerTokenEnhancer;
import top.fan2wan.security.enums.OauthGrantTypeEnum;

import java.util.Arrays;

/**
 * @Author: fanT
 * @Date: 2021/2/3 9:30
 * @Description: config for authorization
 * <p>
 * oauth/token 访问得时候 需要带上basic auth  用户名和密码就是 设置得clientId 和secret
 * 在security-oauth2中 把refresh_token 也变为一种授权类型 所以当用refresh_token刷新access_token得时候
 * 把grant_type 设为refresh_token,在传入refresh_token 就可以获取access_token
 * <p>
 * 对于密码登录--JWT的方式
 * 由于信息保存于JWT中 可以不用持久化保存签发的JWT包括access_token和refresh_token
 * 意味着 即使oauth 服务重启 jwt没有失效 还可以访问接口
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    //告诉Spring Security Token的生成方式
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
//                .tokenEnhancer(tokenEnhancerChain())
                .accessTokenConverter(jwtAccessTokenConverter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    private TokenEnhancer tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomerTokenEnhancer(), jwtAccessTokenConverter));
        return tokenEnhancerChain;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(passwordEncoder.encode("secret"))
                .authorizedGrantTypes(OauthGrantTypeEnum.PASSWORD.getName(), "authorization_code", "client_credentials",
                        OauthGrantTypeEnum.REFRESH_TOKEN.getName())
                .accessTokenValiditySeconds(30)
                .refreshTokenValiditySeconds(60 * 2)
                .scopes("all");
//                .autoApprove(true);
        //登录后绕过批准询问(/oauth/confirm_access)
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }

    /**
     * restTemplate
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(10 * 1000);
        httpRequestFactory.setConnectTimeout(5 * 3000);
        httpRequestFactory.setReadTimeout(5 * 3000);
        return new RestTemplate(httpRequestFactory);
    }
}