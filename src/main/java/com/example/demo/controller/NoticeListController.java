package com.example.demo.controller;

import com.example.demo.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Notice> list = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/newlect";
        String sql = "SELECT * FROM NOTICE";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,"root","7789295951r");
            Statement st = con.createStatement();
            ResultSet rs = st. executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String date = rs.getString("REGDATE");
                String writerID = rs.getString("WRITER_ID");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");
                String content = rs.getString("CONTENT");

                Notice notice = new Notice(
                        id,title,date,writerID,hit,files,content);
                list.add(notice);
            }

            rs.close();
            st.close();
            con.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("list",list);
        request.getRequestDispatcher("/notice/list.jsp").forward(request,response);

    }
}
