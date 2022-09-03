package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/spag")
public class Spag extends HelloServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int num = 0;
        String num_ = request.getParameter("n");
        if (num_ != null && !num_.equals(""))
            num = Integer.parseInt(num_);

        String result;
        if (num%2 != 0)
            result = "odd";
        else
            result = "even";

        request.setAttribute("result",result);

        String[] names = {"new","drgon"};
        request.setAttribute("s",names);

        Map<String, Object> notice = new HashMap<String, Object>();
        //object해서 아무거나 넣을 수 있게
        notice.put("id",1);
        notice.put("title","el good");
        request.setAttribute("notice",notice);


        //redirect 현재 작업한느 것과 관계 없이 새로운 요청 로드
        //forward 현재 작업하는 걸 이어갈 수 있게
        RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");//같은 디렉토리라서 경리 설정 안한 것
        try {
            dispatcher.forward(request,response); //포워드할때는 req,rep 공유 -> 그럼 spag.jsp와 spag.java req,rep 같다
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
