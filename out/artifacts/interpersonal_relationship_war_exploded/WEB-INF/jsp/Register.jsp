<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/9/27
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>注册</h1>
<form action="${pageContext.request.contextPath}/user/register.form" method="post">
    用户名
    <input type="text" name="username">
    姓名
    <input type="text" name="name">
    邮箱
    <input type="text" name="email">
    密码
    <input type="text" name="key">
    确认密码
    <input type="text" name="confirmKey">
    <input type="submit" name="注册">
</body>
</html>
