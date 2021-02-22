package top.fan2wan.security.service.impl;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import top.fan2wan.api.dto.Result;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.api.util.ExceptionUtil;
import top.fan2wan.oauth.dto.LoginDTO;
import top.fan2wan.oauth.param.LoginParam;
import top.fan2wan.security.constant.StrConstant;
import top.fan2wan.security.enums.OauthGrantTypeEnum;
import top.fan2wan.security.service.ITokenService;
import top.fan2wan.security.util.JwtUtil;
import top.fan2wan.web.util.WebUtil;

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
//    public static final String GRANT_TYPE = "grant_type";
//    public static final String CLIENT_ID = "client_id";
//    public static final String SCOPE = "scope";
//    public static final String CLIENT_SECRET = "client_secret";
//    public static final String PASSWORD = "password";
//    public static final String USERNAME = "username";
//    public static final String ACCESS_TOKEN = "access_token";

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

        //设置body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(StrConstant.GRANT_TYPE, loginParam.getGrantType());
        body.add(StrConstant.USERNAME, loginParam.getUsername());
        body.add(StrConstant.PASSWORD, loginParam.getPassword());
        body.add(StrConstant.CLIENT_SECRET, loginParam.getClientSecret());
        body.add(StrConstant.CLIENT_ID, loginParam.getClientId());
        body.add(StrConstant.SCOPE, loginParam.getScope());
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

        LoginDTO loginDTO = new LoginDTO();
        if (Objects.isNull(jwtBody) || !jwtBody.containsKey(StrConstant.ACCESS_TOKEN)) {
            return loginDTO;
        }

        loginDTO.setAccessToken((String) jwtBody.get(StrConstant.ACCESS_TOKEN));
        return loginDTO;
    }

    /**
     * 刷新access_token
     *
     * @return access_token
     */
    @Override
    public Result refresh() {
        return refresh(WebUtil.getAccessToken());
    }

    /**
     * 刷新access_token
     *
     * @param token 这个token 是access_token 但是里面包含了refresh_token
     * @return access_token
     */
    public Result refresh(String token) {
        log.info("refresh -- token was :{}", token);
        Result result = Result.error();
        String refreshToken;
        String clientId;
        String clientSecret;
        try {
            Claims tokenBody = JwtUtil.getTokenBody(token);
            refreshToken = tokenBody.get(StrConstant.REFRESH_TOKEN_KEY).toString();
            clientId = tokenBody.get(StrConstant.CLIENT_ID).toString();
            clientSecret = tokenBody.get(StrConstant.CLIENT_SECRET).toString();
        } catch (Exception e) {
            log.error("refresh -- getRefreshToken error was :{}", e.getMessage());
            result.setCode(MsgCode.TOKEN_INVALID.getCode());
            result.setMessage(MsgCode.TOKEN_INVALID.getMessage());
            return result;
        }

        //设置headers
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        //设置body
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add(StrConstant.GRANT_TYPE, OauthGrantTypeEnum.REFRESH_TOKEN.getName());
        body.add(StrConstant.CLIENT_SECRET, clientSecret);
        body.add(StrConstant.CLIENT_ID, clientId);
        body.add(OauthGrantTypeEnum.REFRESH_TOKEN.getName(), refreshToken);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    ExceptionUtil.throwException(MsgCode.PARAM_ERROR);
                }
            }
        });

        String authUrl = HTTP_LOCALHOST + PORT_SPLIT + serverPort + OAUTH_TOKEN;
        ResponseEntity<Map> exchange = restTemplate.exchange(authUrl, HttpMethod.POST, httpEntity, Map.class);
        Map jwtBody = exchange.getBody();
        log.info("jwtBody was :{}", jwtBody);

        if (Objects.isNull(jwtBody) || jwtBody.containsKey(StrConstant.ERROR)
                || Objects.isNull(jwtBody.get(StrConstant.ACCESS_TOKEN))) {
            // 异常
                    /*{
    "error": "invalid_token",
    "error_description": "Invalid refresh token (expired): eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJmYW50Iiwic2NvcGUiOlsiYWxsIl0sImF0aSI6IjM3ODk3NWNmLWNmOGUtNGMxOS04YTYyLTQzZjgyODEwZmU1YiIsImNsaWVudF9zZWNyZXQiOiJzZWNyZXQiLCJleHAiOjE2MTM5NjA2NDQsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjY4MDk5MmMzLTExMDItNDlmZC04OWZkLWE0NTE1ZTgzZDMwNiIsImNsaWVudF9pZCI6ImNsaWVudCJ9.OMSslCP0d8flSRTWQTQLTw3IZfksa1UovgdaglqZMwQ"
}*/
            //TODO 异常返回
            return result;
        }

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setAccessToken(jwtBody.get(StrConstant.ACCESS_TOKEN).toString());
        result.setResult(loginDTO);
        result.setCode(Result.SUCCESS_CODE);
        result.setMessage(Result.SUCCESS_MSG);
        return result;
    }
}
