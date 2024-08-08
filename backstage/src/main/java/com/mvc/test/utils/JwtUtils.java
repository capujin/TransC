package com.mvc.test.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author qiumin
 * @classname JWTUtils
 * @Description love code
 * @date 2022-07-10 15:33
 */

public class JwtUtils {

    private static final String SING = "!FhSD!#VhDZF%#FDBD";

    /**
     * 获得Token
     * @param map1 header
     * @param map2 payload
     * */
    public static String getToken(Map<String,Object> map1, Map<String,String> map2){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,180); //过期时间

        JWTCreator.Builder builder = JWT.create();
        builder.withHeader(map1); //header

        map2.forEach((k,v)->{builder.withClaim(k,v);}); //payload
        builder.withExpiresAt(instance.getTime()); //过期时间
        String token = builder.sign(Algorithm.HMAC256(SING));//签名
        return token;
    }

    /**
     * 验证token
     * @param token 令牌
     * */
    public static void verify(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SING)).build();
        build.verify(token);
    }

    /**
     * 获取token中的信息
     * @param token 令牌
     * */
    public static DecodedJWT analysis(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

}
