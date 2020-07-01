package com.Aiatian.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {},loadOnStartup = 2)
public class WebPathInitServlet extends HttpServlet{

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("path",config.getServletContext().getContextPath());
        super.init(config);
    }
}