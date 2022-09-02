<%--
  Created by IntelliJ IDEA.
  User: kukjinkim
  Date: 2022-08-29
  Time: 9:18 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//resp.setCharacterEncoding("UTF-8");       -> 지시자에서
//resp.setContentType("text/html; charset=UTF-8");     -> 지시자에서
//PrintWriter out = resp.getWriter();   -> out내장객체 있어서 생성 필요없다다

String cnt_ = request.getParameter("cnt");

int cnt = 100;
if (cnt_ != null && !cnt_.equals(""))
cnt = Integer.parseInt(cnt_);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <% for (int i=0; i<cnt; i++){%>
    hi <br>
    <%}%>
</body>
</html>
