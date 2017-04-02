package com.brojandos.web.course.servlet;


import com.brojandos.web.course.common.Constants;
import com.brojandos.web.course.entity.User;
import com.brojandos.web.course.model.LoginBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Brojandos
 * @creation_date: Mar 16, 2017
 */

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    LoginBean bean;
    User user;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        user = new User();
        user.setName(req.getParameter(Constants.PARAMETER_NAME));
        user.setPassword(req.getParameter(Constants.PARAMETER_PASSWORD));
        req.setAttribute(Constants.MODEL_ATTRIBUTE_NAME, user);
        
        bean = new LoginBean();
        bean.setName(req.getParameter(Constants.PARAMETER_NAME));
        bean.setPassword(req.getParameter(Constants.PARAMETER_PASSWORD));
        req.setAttribute(Constants.MODEL_ATTRIBUTE_NAME, bean);
        
        if (bean.validate()) {
            req.getRequestDispatcher("login-success.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("login-failure.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
}