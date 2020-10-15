<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/login_layout.css">
</head>
<body>
<header>
    <div class="logo">
        <a class="smoothscroll" href="index.jsp"><img src="images/logo_1.png" alt=""></a>
    </div>
    <nav id="nav-wrap">
        <a class="mobile-btn" href="#nav-wrap" title="Show navigation">Show Menu</a>
        <a class="mobile-btn" href="#" title="Hide navigation">Hide Menu</a>
        <ul id="nav" class="nav">
            <li><a class="smoothscroll" href="index.jsp" style="font-size: medium;">Back</a></li>
        </ul>
    </nav>
</header>
    <div class = "content">
        <div class = "panel">
            <form action="${pageContext.request.contextPath}/user/login.form" method="post">
                <div class="group">
                    <label>Username</label>
                    <input placeholder="Please enter username" type = "text" name = "username">
                </div>
                <div class="group">
                    <label>Password</label>
                    <input placeholder="Please enter password" type="password" name = "key">
                </div>
                <div class="login">
                    <input type="submit" value="Login">
                </div>
            </form>
            <div class="register">
                <a href="register_1.jsp"><button>Register</button></a>
            </div>
        </div>
    </div>
</body>
</html>
