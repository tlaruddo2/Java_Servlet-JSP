package com.example.demo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req,
                           HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        resp.setContentType("text/html; charset=UTF-8");

        //_는 임시변수
        String value = req.getParameter("value");
        String operator = req.getParameter("operator");
        String dot = req.getParameter("dot");

        String exp = "0";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("exp")){
                    exp = c.getValue();
                    break;
                }
            }
        }

        if (operator != null && operator.equals("=")) {
            //scriptEngine 자바 스크립트 실행
            //최신버전 JDK에서는 graalvml 이걸로 대체
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            try {
                exp = String.valueOf(engine.eval(exp)); // 스크립트 실행

            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }

        }else if (operator != null && operator.equals("C")){
            exp = "";
        }else{

            exp += (value == null)?"":value;
            exp += (operator == null)?"":operator;
            exp += (dot == null)?"":dot;
        }

        Cookie expCookie = new Cookie("exp", exp);
        if (operator != null && operator.equals("C")){
            expCookie.setMaxAge(0);
        }
        resp.addCookie(expCookie);

        resp.sendRedirect("/calcpage");
        }









    }

