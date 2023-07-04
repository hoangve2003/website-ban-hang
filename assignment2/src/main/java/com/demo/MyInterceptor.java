package com.demo;

import com.demo.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            if (account.isAdmin()) {
                return true; // Cho phép truy cập các tài nguyên admin
            } else {
                response.sendRedirect("/"); // Chuyển hướng đến trang cấm truy cập
                return true;
            }
        }
        response.sendRedirect("/login"); // Chuyển hướng đến trang đăng nhập
        return false;
    }


}
