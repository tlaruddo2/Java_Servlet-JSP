package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add2")
public class Add2 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        double result = 0;

        //_는 임시변수
        String[] num_ = req.getParameterValues("num");

        for (int i=0; i<num_.length; i++){
            Double num = Double.parseDouble(num_[i]);
            result += num;
        }

        PrintWriter out = resp.getWriter();
        out.println("<h1>" + result + "</h1>");
    }
}
