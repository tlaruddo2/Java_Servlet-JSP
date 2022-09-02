package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class Add  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        //_는 임시변수
        String x_ = req.getParameter("number1");
        String y_ = req.getParameter("number2");

        double x = 0;
        double y = 0;

        if (!x_.equals("")) x = Double.parseDouble(x_);
        if (!y_.equals("")) y = Double.parseDouble(y_);

        double sum = x+y;

        PrintWriter out = resp.getWriter();
        out.println("<h1>" + sum + "</h1>");
    }
}
