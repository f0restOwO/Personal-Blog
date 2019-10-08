<%--
  Created by IntelliJ IDEA.
  User: f0rest
  Date: 2019/9/29
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>article_edit</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Blog/static/css/bootstrap4.0.min.css" >
    <script src="/Blog/static/js/jquery.slim.min.js" ></script>
    <script src="/Blog/static/js/popper.min.js" ></script>
    <script src="/Blog/static/js/bootstrap4.0.min.js"></script>
</head>
<body>
<div style="position: relative; top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success" role="alert">
            ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </c:if>
</div>
<div class="container">
    <form action="/Blog/admin/article/edit/do" method="post">
        <input type="hidden" value="${article.id}" name="id">
        <div class="form-group">
            <label for="title">文章标题</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="title" value="${article.title}">
        </div>
        <div class="form-group">
            <label for="catalog_id">栏目</label>
            <select class="form-control" id="catalog_id" name="catalog_id">
                <option value="0" <c:if test="${article.catalog_id==0}">selected="selected"</c:if>>学习</option>
                <option value="1" <c:if test="${article.catalog_id==1}">selected="selected"</c:if>>生活</option>
            </select>
        </div>
        <div class="form-group">
            <label for="keywords">关键字</label>
            <input type="text" class="form-control" id="keywords" name="keywords" placeholder="关键字" value="${article.keywords}">
        </div>
        <div class="form-group">
            <label for="desci">简介</label>
            <textarea class="form-control" id="desci" rows="3" name="desci" placeholder="简介">${article.desci}</textarea>
        </div>
        <div id="cont" style="display: none">
            ${article.content}
        </div>
        <div class="form-group">
            <label for="editor">内容</label>
            <textarea class="form-control" id="content" name="content">${article.content}</textarea>
            <script id="editor" type="text/plain"  name="content" style="width:1024px;height:500px;" >
            </script>
        </div>
        <input type="submit" />
    </form>
    
    <script>
        $(function() {
            var ue = UE.getEditor("editor");
            ue.ready(function() {
                ue.setContent($("#cont").html());
            });
        });
    </script>
</div>
</body>
</html>
