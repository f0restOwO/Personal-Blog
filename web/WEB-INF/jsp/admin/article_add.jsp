<%--
  Created by IntelliJ IDEA.
  User: f0rest
  Date: 2019/9/28
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>article_add</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/Blog/static/css/bootstrap4.0.min.css" >
    <script src="/Blog/static/js/jquery.slim.min.js" ></script>
    <script src="/Blog/static/js/popper.min.js" ></script>
    <script src="/Blog/static/js/bootstrap4.0.min.js"></script>
    <script type="text/javascript" src="/Blog/static/js/wangEditor.min.js"></script>
</head>
<body>
<div style="position: relative; top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success" role="alert">
            ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>
</div>
<div class="container">
    <form action="/Blog/admin/article/add/do" method="post">
        <div class="form-group">
            <label for="title">文章标题</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="文章标题">
        </div>
        <div class="form-group">
            <label for="catalog_id">栏目</label>
            <select class="form-controller" id="catalog_id" name="catalog_id">
                <option value="0">学习</option>
                <option value="1">生活</option>
            </select>
        </div>
        <div class="form-group">
            <label for="keywords">关键字</label>
            <input class="form-controller" id="keywords" name="keywords" placeholder="关键字">
        </div>
        <div class="form-group">
            <label for="desci">简介</label>
            <textarea class="form-controller" id="desci" name="desci" placeholder="简介"></textarea>
        </div>
        <div class="form-group">
            <label for="div1">内容</label>
            <div id="div1">
            </div>
            <textarea id="content" name="content" style="display: none"></textarea>
        </div>
        <input type="submit" value="发表">
    </form>
    <script>
        var E = window.wangEditor;
        var editor = new E("#div1");
        var $text1 = $("#content");
        editor.customConfig.onchange = function(html) {
            $text1.val(html);
        }
        editor.create();
        $text1.val(editor.txt.html());
    </script>
</div>
</body>
</html>
