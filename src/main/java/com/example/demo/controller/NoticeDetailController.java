package com.example.demo.controller;

import com.example.demo.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String url = "jdbc:mysql://localhost:3306/newlect";
        String sql = "SELECT * FROM NOTICE WHERE ID=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,"root","7789295951r");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st. executeQuery();
            rs.next();

            //models (local variable in try-catch)
            String title = rs.getString("TITLE");
            String date = rs.getString("REGDATE");
            String writerID = rs.getString("WRITER_ID");
            String hit = rs.getString("HIT");
            String files = rs.getString("FILES");
            String content = rs.getString("CONTENT");

            //save model in request
            /*
            request.setAttribute("title",title);
            request.setAttribute("date",date);
            request.setAttribute("writerID",writerID);
            request.setAttribute("hit",hit);
            request.setAttribute("files",files);
            request.setAttribute("content",content);
            */

            //entity
            Notice notice = new Notice(
                    id,title,date,writerID,hit,files,content);
            request.setAttribute("n",notice);

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //dispatcher
        request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request,response);

    }
}
