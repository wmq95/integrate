package top.fan2wan.security.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.fan2wan.api.exception.MsgCode;
import top.fan2wan.api.util.ExceptionUtil;

import java.time.Instant;
import java.util.Date;

/**
 * @Author: fanT
 * @Date: 2021/1/28 14:43
 * @Description: util for jwt
 * <p>
 * <p>
 * JWT:三个部分 .作为分隔符
 * <p>
 * header.payload.signature
 * <p>
 * header:  头部 通常存放算法和令牌类型等
 * payload: 载体 三种类型
 * Registered claims 预定义的声明
 * Public claims 公开声明
 * Private claims 私有声明
 * <p>
 * signature:    签名
 */
@Component
@Slf4j
public class JwtUtil {

    public static final String SECRET = "fanT";
    public static final String ISS = "fanT";
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 7L;


    public String generatorJwt(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRE_TIME))
                .compact();
    }

    public String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    public static Claims getTokenBody(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("getTokenBody -- ExpiredJwtException");
//            ExceptionUtil.throwException(MsgCode.TOKEN_INVALID);
//            即使过期 也可以获取claims
            claims = e.getClaims();
        } catch (UnsupportedJwtException e) {
            log.info("getTokenBody -- UnsupportedJwtException, error was :{}", e.getMessage());
            ExceptionUtil.throwException(MsgCode.TOKEN_INVALID);
        } catch (MalformedJwtException e) {
            log.info("getTokenBody -- MalformedJwtException, error was :{}", e.getMessage());
            ExceptionUtil.throwException(MsgCode.TOKEN_INVALID);
        } catch (SignatureException e) {
            log.info("getTokenBody -- SignatureException, error was :{}", e.getMessage());
            ExceptionUtil.throwException(MsgCode.TOKEN_INVALID);
        } catch (IllegalArgumentException e) {
            log.info("getTokenBody -- IllegalArgumentException, error was :{}", e.getMessage());
            ExceptionUtil.throwException(MsgCode.TOKEN_INVALID);
        }
        return claims;
    }

    public static boolean checkJwt(String jwt) {
        boolean flag = false;
        try {
            Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwt)
                    .getBody();
            flag = true;
        } catch (ExpiredJwtException e) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检验token 是否过期
     *
     * @param jwt jwt
     * @return boolean true 没有过期 反之过期
     */
    public static boolean checkExpired(String jwt) {
        Date expirationDate = getTokenBody(jwt).getExpiration();
        return Instant.now().toEpochMilli() < expirationDate.toInstant().toEpochMilli();
    }
}
