package com.bww.shop.utils;

import com.bww.shop.domain.User;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * jwt工具类
 */
public class JWTUtils {

    public static final String SUBJECT = "shop";
    public static long EXPIRE = 1000*60*60*24*7;//过期时间
    public static final String APPSECRET = "shop123456";

    /**
     * 生成jwt
     * @param user
     * @return
     */
    public static String geneJsonWebToken(User user) {

        if (user.getId() == null || user.getUserNm() == null) {
            return null;
        }

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id", user.getId())
                .claim("name", user.getUserNm())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();
        return token;
    }


    public static Claims checkJWT(String token) {


        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        } catch (Exception e) {
            //TODO
            return null;
        }
    }
}
