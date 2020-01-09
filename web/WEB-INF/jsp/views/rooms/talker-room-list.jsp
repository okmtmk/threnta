<%@ page import="models.Room" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2019/12/24
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="参加したルーム一覧"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <div class="row">
        <%
            if (request.getAttribute("error") != null) {
                Exception e = (Exception) request.getAttribute("error");
        %>
        <div class="col-12 text-center text-danger">
            <%= e.getMessage() %>
        </div>
        <%
        } else {
            List<Room> createdRooms = (List<Room>) request.getAttribute("created-rooms");

            List<Room> talkedRooms = (List<Room>) request.getAttribute("talked-rooms");
        %>

        <div class="col-12 col-md-6">
            <h3 class="font-weight-bold">作成したスレッド</h3>
            <%
                if (createdRooms.size() <= 0) {
            %>
            <div class="text-center">
                <small class="text-muted">作成したスレッドはありません。</small>
            </div>
            <%
            } else {
                for (Room it : createdRooms) {
                    request.setAttribute("room", it);
            %>
            <jsp:include page="/WEB-INF/jsp/components/updatable-room-card.jsp"/>
            <%
                    }
                }
            %>
        </div>

        <div class="col-12 col-md-6">
            <h3 class="font-weight-bold">会話したスレッド</h3>
            <%
                if (talkedRooms.size() <= 0) {

            %>
            <div class="text-center">
                <small class="text-muted">会話したスレッドはありません。</small>
            </div>
            <%
            } else {
                for (Room it : talkedRooms) {
                    request.setAttribute("room", it);
            %>
            <jsp:include page="/WEB-INF/jsp/components/room-card.jsp"/>
            <%
                    }
                }
            %>
        </div>

        <%
            }
        %>
    </div>
</div>
</body>
</html>
