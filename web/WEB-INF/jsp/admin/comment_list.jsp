<%--
  Created by IntelliJ IDEA.
  User: f0rest
  Date: 2019/10/1
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>comment_list</title>
    <link rel="stylesheet" href="/Blog/static/css/bootstrap4.0.min.css">
    <script src="/Blog/static/js/jquery-3.2.1.min.js"></script>
    
    <script src="/Blog/static/js/popper.min.js" ></script>
    <script src="/Blog/static/js/bootstrap4.0.min.js"></script>
    <script src="/Blog/static/js/layer.js"></script>
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
    <c:if test="${!empty comments}">
        <table class="table">
            <thead class="thead-default">
            <tr>
                <th>流水号</th>
                <th>评论内容</th>
                <th>日期</th>
                <th>昵称</th>
                <th>邮箱</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${comments}" var="comment">
                <tr>
                    <th scope="row">${comment.id}</th>
                    <td>${comment.content}</td>
                    <td>${comment.date}</td>
                    <td>${comment.name}</td>
                    <td>${comment.email}</td>
                    <td><button type="button" class="btn btn-outline-danger btn-sm" onclick="ifdelete('${comment.id}') ">删除</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <script src="/Blog/static/js/jquery.slim.min.js"></script>
    
        <script>
            function ifdelete(id) {
                layer.confirm("确定删除该评论吗", {btn: ['确定', '取消'
                    ]}, function() {
                    $.ajax({
                        type: "POST",
                        url: "/Blog/api/comment/del",
                        datatype: "json",
                        data: {"id": id},
                        success: function (data) {
                            if (data["stateCode"] == 1) {
                                layer.msg("删除成功", {icon: 1, time: 1000});
                                setTimeout("window.location.reload()", 1000);
                            } else {
                                layer.msg("删除失败", {icon: 5, time: 1000})
                            }
                        },
                        error: function (data) {
                            console.log("错误...");
                            
                        }
                    });
                });
            }
        </script>
    </c:if>
    <c:if test="${empty comments}">
        <div class="card">
            <div class="card-body">
                该文章无评论
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
