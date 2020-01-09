<%@ page import="models.Room" %><%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/09
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Room room = (Room) request.getAttribute("room");
%>
<div class="card border-0 shadow-sm">
    <div class="card-body">
        <h6 class="card-title font-weight-bold">
            <%= room.getName() %>
        </h6>
        <p class="text-muted">
            <%= room.getDescription() %>
        </p>
        <p class="text-muted text-right">
            更新日 : <%= room.getUpdatedAt() %>
        </p>
        <a class="btn btn-primary" href="/threnta?thread=<%=room.getId()%>">
            開く
        </a>
        <a class="btn btn-secondary" href="/threnta/threads/update?thread=<%=room.getId()%>">
            編集
        </a>
    </div>
</div>
