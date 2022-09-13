package com.lxyk.controller;

import com.lxyk.pojo.User;
import com.lxyk.service.UserService;
import com.lxyk.service.impl.UserServiceimpl;

import javax.servlet.http.Cookie;
import java.io.IOException;

@javax.servlet.annotation.WebServlet("/Login")
public class Login extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        String verifycode = request.getParameter("verifycode");

        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //为了保证验证码的一致性
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!(verifycode != null && checkcode_server.equalsIgnoreCase(verifycode))) {
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        User logining_user = new User(username,password);
        UserService userService = new UserServiceimpl();
        User logined_user = userService.login(logining_user);

        if (logined_user != null){
            if ("1".equalsIgnoreCase(remember)){
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                c_username.setMaxAge(24*60*60);
                c_password.setMaxAge(24*60*60);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }
            //登录成功

        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }
}
