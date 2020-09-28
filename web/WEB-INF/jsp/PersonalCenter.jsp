<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/9/27
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>个人中心</h1>
<form action="${pageContext.request.contextPath}/user/PersonalCenter.form" method="post">
    用户名
    <input type="text" name="username">
    密码
    <input type="text" name="key">
    <input type="submit" name="进入个人中心">
</body>

</body>
</html>
