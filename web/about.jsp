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
    <h1 class="bg-dark text-white font-weight-bold">
        Thread Talking App → Threnta
    </h1>
    <div class="lead text-muted mb-5">
        「Threnta」はシンプルなチャットアプリです。<br>
        誰でも簡単にチャットができます。
    </div>
    <h2 class="bg-dark text-white font-weight-bold">スレッドを立てて会話しよう</h2>
    <div class="lead text-muted mb-5">
        スレッドごとに話題は分かれています。<br>
        話したい話題のスレッドがない場合は新しく作って、会話を初めてみましょう。
    </div>
    <h2 class="bg-dark text-white font-weight-bold">
        使ってみよう
    </h2>
    <a href="/threnta/threads/create.jsp" class="btn btn-link">スレッドの登録</a>
    <a href="/threnta" class="btn btn-link">全スレッド表示</a>
    <a href="/threnta/threads/pk-search.jsp" class="btn btn-link">検索</a>
</div>
</body>
</html>
