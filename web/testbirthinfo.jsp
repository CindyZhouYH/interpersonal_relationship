<%--
  Created by IntelliJ IDEA.
  User: 圆
  Date: 2020/11/15
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/birthinfo/addBirthInfo.form" method="post">
    <tr><td>你爹姓啥:</td><td><Input type=text
                                 name="father_name" >*</td></tr>
    <tr><td>你爹祖籍在哪:</td><td><Input type=text
                                   name="father_place">*</td></tr>
    <tr><td>你妈姓啥:</td><td><Input type=text
                                 name="mother_name"></td></tr>
    <tr><td>你妈祖籍在哪:</td><td><Input type=text
                                   name="mother_place"></td></tr>
    <tr><td>你哪年出生的:</td><td><Input type=text
                                   name="year"></td></tr>
    </tr><tr><td><Input type=submit value="提交"></td> </tr>
</form>
</body>
</html>
