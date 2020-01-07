<%@ page import="models.Message" %>
<%@ page import="models.Room" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/07
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="トーク画面"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <%
        if (request.getAttribute("error") != null) {
            Exception e = (Exception) request.getAttribute("error");
    %>

    <%
    } else {
        Room room = (Room) request.getAttribute("room");
        List<Message> messages = (List<Message>) request.getAttribute("messages");
    %>
    <div class="col-lg-6 col-12 mx-auto">
        <form action="/threnta/messages" method="post">
            <div class="form-group">
                <label for="message">メッセージ投稿</label>
                <textarea id="message"
                          class="form-control"
                          name="message"></textarea>
            </div>
            <input type="hidden" name="thread" value="<%=room.getId()%>">
            <div class="text-right">
                <button class="btn btn-primary">
                    投稿
                </button>
            </div>
        </form>
    </div>
    <div class="col-12">
        <hr>
    </div>
    <%
        for (Message message : messages) {
    %>
    <div class="col-12">
        <p>
            <%=message.getMessage()%>
        </p>
        <small class="text-muted"><%=message.getCreatedAt()%></small>
        <hr>
    </div>
    <%
        }
    %>
    <div class="col-12">
        <div class="text-center text-muted">
            ・
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
