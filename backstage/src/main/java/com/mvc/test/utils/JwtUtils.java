package com.mvc.test.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private String secretKey = ")!@#1$2(3#&#c@!!@#"; // 密钥
//    生成令牌
    public String generateToken(String id) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(id)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10000 * 60 * 60)) // 1小时过期
                .sign(algorithm);
    }
//    解码令牌
    public DecodedJWT decodeToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

//    提取ID
    public String extractId(String token) {
        return decodeToken(token).getSubject();
    }
//    令牌是否过期
    public boolean isTokenExpired(String token) {
        return decodeToken(token).getExpiresAt().before(new Date());
    }
//    验证令牌
    public boolean validateToken(String token, String id) {
        return (id.equals(extractId(token)) && !isTokenExpired(token));
    }
}
