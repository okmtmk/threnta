<%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2020/01/09
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/WEB-INF/jsp/views/base/head.jsp">
    <jsp:param name="title" value="Primary Key検索"/>
</jsp:include>
<body class="bg-light">
<jsp:include page="/WEB-INF/jsp/views/base/navber.jsp"/>
<div class="container">
    <div class="col-12">
        <div class="card shadow-sm border-0">
            <div class="card-body">
                <h3 class="card-title font-weight-bold">
                    スレッドのPK検索
                </h3>
                <%
                    if (request.getAttribute("error") != null) {
                %>
                <small class="text-danger">
                    <i class="fas fa-exclamation-triangle"></i> 一致するレコードが見つかりませんでした。
                </small>
                <%
                    }
                %>

                <form action="/threnta/threads/pk" method="get">
                    <div class="form-group">
                        <label for="pk">主キー</label>
                        <input class="form-control" id="pk" name="pk" type="number">
                    </div>
                    <button type="submit" class="btn btn-primary">検索</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
