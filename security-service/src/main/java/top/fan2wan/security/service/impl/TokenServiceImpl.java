package top.fan2wan.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.api.util.ExceptionUtil;
import top.fan2wan.oauth.dto.LoginDTO;
import top.fan2wan.oauth.param.LoginParam;
import top.fan2wan.security.service.ITokenService;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/2/21 8:52
 * @Description: impl for tokenService
 */
@Service
@Slf4j
public class TokenServiceImpl implements ITokenService {

    public static final String HTTP_LOCALHOST = "http://localhost";
    public static final String PORT_SPLIT = ":";
    public static final String OAUTH_TOKEN = "/oauth/token";
    public static final String BASIC = "Basic ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String SCOPE = "scope";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String ACCESS_TOKEN = "access_token";

    private final RestTemplate restTemplate;

    @Value("${server.port}")
    private String serverPort;

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 用户登录
     *
     * @param loginParam param
     * @return LoginDTO
     */
    @Override
    public LoginDTO login(LoginParam loginParam) {
        log.info("login -- param was :{}", loginParam);
        ExceptionUtil.checkException(Objects.nonNull(loginParam) && Objects.nonNull(loginParam.getUsername()), MsgCode.PARAM_ERROR);

        String authUrl = HTTP_LOCALHOST + PORT_SPLIT + serverPort + OAUTH_TOKEN;

        //设置headers
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String httpBasic = this.getHttpBasic(loginParam.getClientId(), loginParam.getClientSecret());
        headers.add(AUTHORIZATION, httpBasic);

        //设置body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(GRANT_TYPE, loginParam.getGrantType());
        body.add(USERNAME, loginParam.getUsername());
        body.add(PASSWORD, loginParam.getPassword());
        body.add(CLIENT_SECRET, loginParam.getClientSecret());
        body.add(CLIENT_ID, loginParam.getClientId());
        body.add(SCOPE, loginParam.getScope());
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    ExceptionUtil.throwException(MsgCode.PARAM_ERROR);
                }
            }
        });
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        Map jwtBody = exchange.getBody();
        log.info("jwtBody was :{}", jwtBody);
/*        if (jwtBody == null || jwtBody.get(ACCESS_TOKEN) == null || jwtBody.get(REFRESH_TOKEN) == null || jwtBody.get(JTI) == null) {
            log.info("exchange {}", exchange);
            log.info("jwtBody {}", jwtBody);
            Result result = new Result();
            if (HttpStatus.OK.value() == exchange.getStatusCode().value()) {
                result.setCode((Integer) jwtBody.get(RESULT_CODE));
                result.setMessage((String) jwtBody.get(RESULT_MESSAGE));
            } else if (HttpStatus.UNAUTHORIZED.value() == exchange.getStatusCode().value()) {
                result.setCode(MsgCodeEnum.CLIENT_ERROR.code());
                result.setMessage(MsgCodeEnum.CLIENT_ERROR.msg());
            } else {
                result.setCode(exchange.getStatusCode().value());
                result.setMessage((String) jwtBody.get(ERROR_DESCRIPTION));
            }
            return result;
        }*/

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccessToken((String) jwtBody.get(ACCESS_TOKEN));
        return loginDTO;
    }


    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + PORT_SPLIT + clientSecret;
        byte[] bytes = Base64Utils.encode(string.getBytes());
        return BASIC + new String(bytes);
    }
}
