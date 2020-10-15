<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/9/27
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>注销</h1>
<form action="${pageContext.request.contextPath}/user/delete.form" method="post">
    用户名
    <input type="text" name="username">
    姓名
    <input type="text" name="name">
    邮箱
    <input type="text" name="email">
    密码
    <input type="text" name="key">
    <input type="submit" name="注销">
</body>
</html>
