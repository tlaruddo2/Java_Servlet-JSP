package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hi")
public class Nana extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        //cnt_ : 마지막에_는 임시변수
        String cnt_ = req.getParameter("cnt");

        int cnt = 100;
        if (cnt_ != null && !cnt_.equals(""))
            cnt = Integer.parseInt(cnt_);


        for (int i=0; i<cnt; i++){
            out.println((i+1) +": 안녕 Hello<br/>");
        }
    }
}
