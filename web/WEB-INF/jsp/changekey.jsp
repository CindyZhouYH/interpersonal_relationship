<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/9/27
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>修改密码</h1>
<form action="${pageContext.request.contextPath}/user/changeKey.form" method="post">

    用户名
    <input type="text" name="username">
    旧密码
    <input type="text" name="oldKey">
    新密码
    <input type="text" name="newKey">
    确认新密码
    <input type="text" name="confirmKey">
    <input type="submit" name="修改密码">
</body>
</html>
