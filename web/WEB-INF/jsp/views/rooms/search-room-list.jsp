<%@ page import="models.Room" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/09
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String search = (String) request.getAttribute("search");
%>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="スレッドの検索"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <h2 class="font-weight-bold">「<%=search%>」の検索結果</h2>
    <%
        if (request.getAttribute("error") != null) {
            Exception e = (Exception) request.getAttribute("error");
    %>
    <div class="text-danger">
        <%=e.getLocalizedMessage()%>
    </div>
    <%
    } else {
        List<Room> rooms = (List<Room>) request.getAttribute("rooms");
    %>
    <div class="row">
        <%
            for (Room it : rooms) {
                request.setAttribute("room", it);
        %>
        <div class="col-12 mb-3">
            <jsp:include page="/WEB-INF/jsp/components/room-card.jsp"/>
        </div>
        <%
            }
        %>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
