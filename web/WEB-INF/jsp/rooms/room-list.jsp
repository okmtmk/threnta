<%@ page import="models.Room" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2019/12/20
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/base/head.jsp">
    <jsp:param name="title" value="スレッド一覧"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/base/navber.jsp"/>
<div class="container mt-3">
    <h1 class="font-weight-bold">スレッド一覧</h1>

    <% if (request.getAttribute("error") != null) {
        Exception e = (Exception) request.getAttribute("error");
    %>
    <div class="text-danger">
        <%=e.getMessage()%>
    </div>
    <% } else {
        List<Room> rooms = (List<Room>) request.getAttribute("rooms");
    %>

    <div class="row">
        <% for (Room it : rooms) { %>
        <div class="col-12 mb-3">
            <div class="card border-0 shadow-sm">
                <div class="card-body">
                    <h6 class="card-title">
                        <%=it.getName()%>
                    </h6>
                    <p class="text-muted">
                        <%=it.getDescription()%>
                    </p>
                    <p class="text-muted text-right">
                        作成日時 : <%=it.getCreatedAt()%>
                    </p>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <% } %>
</div>
</body>
</html>
