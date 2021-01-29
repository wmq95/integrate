package top.fan2wan.security.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

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
        String username;
        try {
            username = getTokenBody(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private static Claims getTokenBody(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
        } catch (MalformedJwtException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return claims;
    }
}
