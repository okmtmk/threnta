<%--
  Created by IntelliJ IDEA.
  User: okamoto
  Date: 2019/12/20
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm mb-3">
    <a class="navbar-brand" href="/threnta">スレンタ</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/threnta/about.jsp">TOP</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/threnta/threads/pk-search.jsp">PK検索</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/threnta">スレッド一覧</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/threnta/threads/create.jsp">スレッド作成</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/threnta/threads/user">参加したスレッド一覧</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/threnta" method="get">
            <input class="form-control mr-sm-2" type="search"
                   placeholder="スレッド名検索" aria-label="スレッド名検索"
                   name="search"
            >
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </form>
    </div>
</nav>
