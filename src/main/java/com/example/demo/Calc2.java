package com.example.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext application = req.getServletContext();
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        PrintWriter out = resp.getWriter();

        resp.setContentType("text/html; charset=UTF-8");

        //_는 임시변수
        String v_ = req.getParameter("v");
        //버튼의 name 통해서 value 받기
        String op = req.getParameter("button");

        double v = 0;

        if (!v_.equals("")) v = Double.parseDouble(v_);

        if(op.equals("=")){
//            double x = (Double) application.getAttribute("value");
//            double x = (Double) session.getAttribute("value");
            double x = 0;
            for (Cookie c : cookies){
                if(c.getName().equals("value")){
                    x = Double.parseDouble(c.getValue());
                    break;
                }
            }

            double y = v;
//            String operator = (String)application.getAttribute("op");
            String operator = "";
            for (Cookie c : cookies){
                if(c.getName().equals("op")){
                    operator = c.getValue();
                    break;
                }
            }
            double result = 0;
            if (operator.equals("+")){
                result = x+y;
            }else {
                result = x-y;
            }

            out.println("<h1>" + result + "</h1>");
        }else {
//            application.setAttribute("value",v); //두고두고 쓸 수 있다
//            application.setAttribute("op",op);
//            session.setAttribute("value",v); //두고두고 쓸 수 있다
//            session.setAttribute("op",op);

            Cookie valueCookie = new Cookie ("value",String.valueOf(v));
            Cookie opCookie = new Cookie("op",op);
            valueCookie.setPath("/calc2"); //모든 url마다 이 쿠키를 가져와라
            valueCookie.setMaxAge(24*60*60);  //만료 날짜로 초단위 >24시간
            opCookie.setPath("/calc2");
//            opCookie.setPath("/notice/"); notice 하위 모든 url마다 쿠키를 가져와라
//            opCookie.setPath("/notice"); notice 에서만
            resp.addCookie((valueCookie));
            resp.addCookie(opCookie);

            resp.sendRedirect("calc2.html");
        }









    }
}
