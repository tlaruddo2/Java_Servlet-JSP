
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%----------------------------------------%>
<html>
<head>
    <title>Title</title>
</head>
<%
//    pageContext.setAttribute("result","hello");
%>
<body>
    this is <%=request.getAttribute("result")%>
    this is ${requestScope.result}<br>
    this is ${s[0]}<br>
    ${notice.title}<br>
    ${aa}<br>
    ${param.n > 3 }<br>
<%--    ${param.n == null || param.n="" ? "값이 비어있씁니다." : param.m}<br>--%>
    ${empty param.n? '값이 비어있습니다' : param.n }<br>
<%--    <%param.n/2 : 3/2 == 1.5 인트 나누기 인트 하더라도 1.5로 나옴%>    --%>
    ${param.n /2}<br>
    ${header.accept}<br>

</body>
</html>
