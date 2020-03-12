package com.bww.shop;

import com.bww.shop.domain.User;
import com.bww.shop.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

public class CommenTest {

    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(123);
        user.setUserNm("bw");

        String token = JWTUtils.geneJsonWebToken(user);
        System.out.println(token);

    }

    @Test
    public void testCheck(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaG9wIiwiaWQiOjEyMywibmFtZSI6ImJ3IiwiaWF0IjoxNTg0MDI2MDYwLCJleHAiOjE1ODQ2MzA4NjB9.nY4uyXTYy5uCCf9wYGVwwu3ErR-YqrjsqyHiwZPgjXM";

        Claims claims = JWTUtils.checkJWT(token);

        if (claims != null) {
            Object id = (Integer) claims.get("id");
            Object userNm = (String)claims.get("name");
            System.out.println(id);
            System.out.println(userNm);
        } else {
            System.out.println("fail");
        }
    }
}
