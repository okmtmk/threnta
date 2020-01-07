<%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/07
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="アプリケーションエラー"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <div class="col-12">
        <div class="card bg-danger text-white">
            <div class="card-body">
                <h6 class="card-title font-weight-bold">
                    エラーが発生しました
                </h6>
                <p class="card-text">
                    <%=exception.getLocalizedMessage()%>
                </p>
                <a href="/threnta" class="btn btn-outline-light">戻る</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
