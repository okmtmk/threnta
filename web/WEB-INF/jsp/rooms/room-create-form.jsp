<%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2019/12/21
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/base/head.jsp">
    <jsp:param name="title" value="スレッドの作成"/>
</jsp:include>
<body>
<jsp:include page="/WEB-INF/jsp/base/navber.jsp"/>
<div class="container">
    <div class="card">
        <div class="card-body">
            <h6 class="card-title font-weight-bold">
                スレッドの作成
            </h6>
            <form action="/threnta/rooms/create" method="post">
                <div class="form-group">
                    <label for="name">スレッド名</label>
                    <input type="text" class="form-control" id="name" name="name"/>
                </div>

                <div class="form-group">
                    <label for="description">説明</label>
                    <textarea class="form-control" id="description" name="description" rows="5"></textarea>
                </div>
                <div class="text-right">
                    <button class="btn btn-primary" type="submit">
                        作成
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
