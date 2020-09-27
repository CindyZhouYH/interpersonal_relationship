<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/9/27
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
</head>
<body>
<h1>登录</h1>
<form action="${pageContext.request.contextPath}/user/login.form" method="post">
    用户名
    <input type="text" name="username">
    密码
    <input type="text" name="key">
    <input type="submit" name="登录">
</body>
</html>
