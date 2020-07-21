package com.bww.shop.intercepter;

import com.bww.shop.common.ResultCode;
import com.bww.shop.domain.Result;
import com.bww.shop.utils.JWTUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor {

    private static final Gson gson = new Gson();
    /**
     * 进入controller拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("-------"+request.getRequestURL());
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        if (token != null) {
            Claims claims = JWTUtils.checkJWT(token);
            if (claims != null) {
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id", id);
                request.setAttribute("name", name);

                return true;
            }

        }
        senfJosnMassage(response, Result.error(ResultCode.USER_NOT_LOGGED_IN,"请登录"));
        return false;
    }

    /**
     * 响应数据给前端
     * @param response
     * @param o
     */
    public static void senfJosnMassage(HttpServletResponse response, Object o) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(gson.toJson(o));
        writer.close();
        response.flushBuffer();

    }
}
