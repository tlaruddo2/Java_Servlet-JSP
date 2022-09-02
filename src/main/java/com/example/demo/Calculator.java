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
import java.io.PrintWriter;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getMethod().equals("GET")){ //대문자로 비교!
//            System.out.println("get come");
//        }else if(req.getMethod().equals("POST")){
//            System.out.println("post come");
//        }
//        super.service(req, resp); // 부모가 가지고 있는 서비스 함수 호출
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String exp = "0";
        Cookie[] cookies = req.getCookies();
        if (cookies != null){ //쿠키가 하도 없으면 null
            for (Cookie c: cookies){
                if(c.getName().equals("exp")){
                    exp = c.getValue();
                    break;
                }
            }
        }

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        //write는 문자열 전용 출력 함수
        out.write("<!DOCTYPE html>");
        out.write("        <html lang=\"en\">");
        out.write("        <head>");
        out.write("            <meta charset=\"UTF-8\">");
        out.write("            <title>add calculator</title>");
        out.write("            <style>");
        out.write("                        input{");
        out.write("                    width: 50px;");
        out.write("                    height: 50px;");
        out.write("                }");
        out.write("                .output{");
        out.write("                    height: 50px;");
        out.write("                    background: #e9e9e9;");
        out.write("                    font-size: 23px;");
        out.write("                    font-weight: bold;");
        out.write("                    text-align: right;");
        out.write("                    padding: 0px 5px;");
        out.write("                }");
        out.write("            </style>");
        out.write("        </head>");
        out.write("        <body>");
        out.write("          <form method=\"post\">"); //자기 페이지 요청할때는 action 필요없다
        out.write("              <table>");
        out.write("                  <tr>");
        out.printf("                      <td class = \"output\" colspan=\"4\">%s</td>",exp);
        out.write("                  </tr>");
        out.write("                  <tr>");
        out.write("                      <td><input type=\"submit\" value=\"CE\" name=\"operator\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"C\" name=\"operator\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"BS\" name=\"operator\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"/\" name=\"operator\"/></td>");
        out.write("                  </tr>");
        out.write("                  <tr>");
        out.write("                      <td><input type=\"submit\" value=\"7\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"8\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"9\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"*\" name=\"operator\"/></td>");
        out.write("                  </tr>");
        out.write("                  <tr>");
        out.write("                      <td><input type=\"submit\" value=\"4\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"5\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"6\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"-\" name=\"operator\"/></td>");
        out.write("                  </tr>");
        out.write("                  <tr>");
        out.write("                      <td><input type=\"submit\" value=\"0\" name=\"value\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\".\" name=\"dot\"/></td>");
        out.write("                      <td><input type=\"submit\" value=\"=\" name=\"operator\"/></td>");
        out.write("                  </tr>");

        out.write("              </table>");
        out.write("          </form>");

        out.write("        </body>");
        out.write("        </html>");

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            //graalvml 최선버전에서는 이걸로 대체
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
        expCookie.setPath("/calculator"); //여기서만 쿠키
        resp.addCookie(expCookie);

        resp.sendRedirect("calculator"); //자기 자신 get요청
    }
}
