package org.apache.maven.interceptors;

import org.apache.maven.clients.UsersAPI;
import org.apache.maven.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final String XCALLERID = "x-caller-id";

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String xCallerId = request.getHeader(XCALLERID);
        UserModel authorizedUser = UsersAPI.GetUser(Integer.parseInt(xCallerId));
        if(authorizedUser == null || authorizedUser.getId() == 0){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        return true;
    }
}