<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/11/16
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/search.form" method="post">
    <tr><td>对方name:</td><td><Input type=text
                                   name="name">*</td></tr>
    </tr><tr><td><Input type=submit value="提交"></td> </tr>
</form>
</body>
</html>
