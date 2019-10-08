<%--
  Created by IntelliJ IDEA.
  User: f0rest
  Date: 2019/9/29
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>article_detail</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Blog/static/css/bootstrap4.0.min.css" >
    <script src="/Blog/static/js/jquery.slim.min.js" ></script>
    <script src="/Blog/static/js/popper.min.js" ></script>
    <script src="/Blog/static/js/bootstrap4.0.min.js"></script>
</head>
<body>
<table class="table table-striped table-sm">
    <tr class="table-active">
        <th width="15%">ID</th>
        <td>${article.id}</td>
    </tr>
    <tr class="table-secondary">
        <th>标题</th>
        <td class="table-sucess">
            ${article.title}
        </td>
    </tr>
    <tr class="table-success">
        <th>关键字</th>
        <td>${article.keywords}</td>
    </tr>
    <tr class="table-danger">
        <th>简介</th>
        <td>${article.desci}</td>
    </tr>
    <tr class="table-warning">
        <th>发表时间</th>
        <td>${article.time}</td>
    </tr>
    <tr class="table-info">
        <th>点击量</th>
        <td>${article.click}</td>
    </tr>
    <tr class="table-light">
        <th>内容</th>
        <td>${article.content}</td>
    </tr>
</table>
</body>
</html>
