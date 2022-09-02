package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/notice-reg")
public class NoticeReg  extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

//        req.setCharacterEncoding("UTF-8"); //필터로 한번에 설정 해보기

        //받은 데이터 파일 입출력이나 SQL로 저장 가능
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        //여기선 그냥 돌려서 보여주기
        out.println(title);
        out.println(content);

    }
}
