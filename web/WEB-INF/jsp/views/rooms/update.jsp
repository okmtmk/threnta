<%@ page import="models.Room" %><%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/09
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="スレッド情報の編集"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<%
    Room room = (Room) request.getAttribute("room");
%>
<div class="container">
    <div class="card shadow-sm border-0">
        <div class="card-body">
            <h6 class="card-title font-weight-bold">
                スレッドの作成
            </h6>
            <form action="/threnta/threads/update" method="post">
                <input type="hidden" name="thread" value="<%=room.getId()%>">
                <div class="form-group">
                    <label for="name">スレッド名</label>
                    <input type="text" class="form-control" id="name" name="name" value="<%=room.getName()%>"/>
                </div>

                <div class="form-group">
                    <label for="description">説明</label>
                    <textarea class="form-control" id="description" name="description" rows="5"><%=room.getDescription()%></textarea>
                </div>
                <div class="text-right">
                    <button class="btn btn-primary" type="submit">
                        更新
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
