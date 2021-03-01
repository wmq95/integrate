package top.fan2wan.gateway.manager;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import top.fan2wan.gateway.bo.AccessTokenBO;
import top.fan2wan.oauth.dto.ValidTokenDTO;
import top.fan2wan.oauth.feign.ITokenFeign;

/**
 * @Author: fanT
 * @Date: 2021/3/1 8:46
 * @Description: manager for oauth
 */
@Service
public class OauthManager {

    private static Logger logger = LoggerFactory.getLogger(OauthManager.class);

    private final ITokenFeign tokenFeign;

    public OauthManager(ITokenFeign tokenFeign) {
        this.tokenFeign = tokenFeign;
    }

    /**
     * 检验token
     *
     * @param token token
     * @return AccessTokenBO
     */
    public AccessTokenBO validToken(String token) {
        AccessTokenBO bo = new AccessTokenBO();

        if (StrUtil.isBlank(token)) {
            return bo;
        }
        ValidTokenDTO feignResult = tokenFeign.validToken(token).result();
        logger.info("validToken -- feignResult was :{}", feignResult);
        bo.setIsExpired(feignResult.getIsExpired());
        bo.setIsValid(feignResult.getIsValid());
        return bo;
    }

    /**
     * 刷新token
     *
     * @return token
     */
    public String refreshToken() {

        return tokenFeign.refresh().result().getAccessToken();
    }
}
