<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>register_1</title>
    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/register_layout.css">
    <link rel="stylesheet" href="css/register.css">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
    </script>

    <script>
        function checkEmailDuplicate() {
            var email = document.getElementById("emailInput").value;
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/register/checkEmailDuplicate",
                data: {"email": email},
                success: function (data) {
                    if (data.toString() == "OK") {
                        document.getElementById("emailInfo").style.color = "green";
                    } else {
                        document.getElementById("emailInfo").style.color = "red";
                    }
                    document.getElementById("emailInfo").innerHTML = data;
                }
            })
        }
    </script>
</head>
<body>
<header>
    <div class="logo">
        <a class="smoothscroll" href="index.jsp"><img alt="" src="images/logo_1.png"></a>
    </div>

    <nav id="nav-wrap">

        <a class="mobile-btn" href="#nav-wrap" title="Show navigation">Show Menu</a>
        <a class="mobile-btn" href="#" title="Hide navigation">Hide Menu</a>

        <ul id="nav" class="nav">
            <li><a class="smoothscroll" href="index.jsp" style="font-size: medium;">Back</a></li>
        </ul> <!-- end #nav -->
    </nav> <!-- end #nav-wrap -->
</header> <!-- Header End -->
<div class="content">
    <div class="panel">
        <form action="${pageContext.request.contextPath}/user/register.form" method="post">
            <div class="re">Registration</div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Username: <input type="text" id="usernameInput" name="username">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Password: <input type="password" id="keyInput" name="key">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Confirm Password: <input type="password" id="confirmKeyInput" name="confirmKey">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Name: <input type="text" id="nameInput" name="name">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            E-mail: <input type="text" id="emailInput" name="email" onblur="checkEmailDuplicate()">
                    </span>
                    <span id="emailInfo"></span>
                </div>
            </div>
            <div class="login">
                <input type="submit" value="Submit" style="font-size: 18px;">
            </div>
        </form>
    </div>
</div>
</body>
</html>
