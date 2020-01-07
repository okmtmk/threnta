<%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/07
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="トーク画面"/>
</jsp:include>
<body>
<jsp:include page="WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <div class="col-lg-6 col-12 mx-auto">
        <form action="#" method="post">
            <div class="form-group">
                <label for="message">メッセージ投稿</label>
                <textarea id="message"
                          class="form-control"
                          name="message"></textarea>
            </div>
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
    <div class="col-12">
        <p>
            メッセージ内容
        </p>
        <small class="text-muted">2020-01-07 12:00:00</small>
        <hr>
    </div>
    <div class="col-12">
        <p>
            メッセージ内容
        </p>
        <small class="text-muted">2020-01-07 12:00:00</small>
        <hr>
    </div>
    <div class="col-12">
        <p>
            メッセージ内容
        </p>
        <small class="text-muted">2020-01-07 12:00:00</small>
        <hr>
    </div>
    <div class="col-12">
        <div class="text-center text-muted">
            ・
        </div>
    </div>
</div>
</body>
</html>
