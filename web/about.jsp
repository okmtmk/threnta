<%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/07
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="About"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <h1>Thread Talk → Threnta</h1>
    <div class="lead text-muted mb-3">
        「Threnta」はシンプルなチャットアプリです。
    </div>
    <h2>スレッドを立てて会話しよう</h2>
    <div class="lead text-muted mb-3">
        スレッドごとに話題は分かれています。話したい話題のスレッドがない場合は新しく作って、会話を初めてみましょう。
    </div>
</div>
</body>
</html>
