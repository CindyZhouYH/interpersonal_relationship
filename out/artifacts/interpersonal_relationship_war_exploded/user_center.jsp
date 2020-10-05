<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>User_center</title>
    <link rel="stylesheet" href="css/user_center.css">
    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/user_center_layout.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script>
        function getUserProfile() {
            $.post({
                url: "${pageContext.request.contextPath}/user/PersonalCenter/PersonalInfo",
                data: {},
                success: function (data) {
                    var msg = JSON.parse(data);
                    console.log(msg.username);
                    console.log(msg.name);
                    console.log(msg.email);
                    console.log(msg.key);
                    var uname = msg.username;
                    var name = msg.name;
                    var email = msg.email;
                    var key = msg.key;
                    document.getElementById("username").value = uname;
                    document.getElementById("name").value = name;
                    document.getElementById("password").value = key;
                    document.getElementById("email").value = email;
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
            $.post({
                url: "${pageContext.request.contextPath}/user/PersonalCenter/EntranceInfo",
                data: {},
                success: function (data) {
                    var msg = JSON.parse(data);
                    var i;
                    console.log(msg);
                    for (i = 1; i <= msg.length; i++) {
                        console.log("school" + i);
                        console.log(msg[i - 1].school_id);
                        document.getElementById("school" + i).value = msg[i - 1].name;
                        document.getElementById("id" + i).value = msg[i - 1].id;
                        document.getElementById("y" + i).value = msg[i - 1].year;
                        document.getElementById("school_" + i).innerHTML = "School" + i + ":";
                        document.getElementById("id_" + i).innerHTML = "Identity:";
                        document.getElementById("y_" + i).innerHTML = "Year:";
                    }
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
        }
    </script>
    <script>
        function logOff() {
            var uname = "";
            var name = "";
            var email = "";
            var key = "";
            $.post({
                url: "${pageContext.request.contextPath}/user/PersonalCenter/PersonalInfo",
                data: {},
                async: false,
                success: function (data) {
                    var msg = JSON.parse(data);
                    uname = msg.username;
                    name = msg.name;
                    email = msg.email;
                    key = msg.key;
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
            $.post({
                url: "${pageContext.request.contextPath}/user/delete",
                data: {"username": uname, "name": name,
                    "email": email, "key": key},
                success: function (data) {
                    alert(data);
                    location.href="index.jsp";
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
        }
    </script>
</head>
<body onload="getUserProfile()">
<header>
    <div class="logo">
        <a class="smoothscroll" href="index.html"><img alt="" src="images/logo_1.png"></a>
    </div>
    <nav id="nav-wrap">
        <a class="mobile-btn" href="#nav-wrap" title="Show navigation">Show Menu</a>
        <a class="mobile-btn" href="#" title="Hide navigation">Hide Menu</a>
        <ul id="nav" class="nav">
            <li><a class="smoothscroll" href="index.html" style="font-size: medium;">Back</a></li>
        </ul>
    </nav>
    <ul class="header-social">
        <li>
            <button style="text-decoration: none;" onclick="logOff()"><span>logOff</span></button>
        </li>
    </ul>
</header>
<section id="hero">
    <div class="content">
        <div class="panel">
            <div class="uc">User Center</div>
            <form action="" method="post">
                <table class="table1">
                    <thead>
                    <th>Username:</th>
                    <th>Password:</th>
                    <th>Name:</th>
                    <th>Email:</th>
                    </thead>
                    <tr>
                        <td><input type="text" id="username"></td>
                        <td><input type="text" id="password"></td>
                        <td><input type="text" id="name"></td>
                        <td><input type="text" id="email"></td>
                    </tr>
                    <thead>
                    <th id="school_1"></th>
                    <th id="id_1"></th>
                    <th id="y_1"></th>
                    </thead>
                    <tr>
                        <td><input type="text" id="school1"></td>
                        <td><input type="text" id="id1"></td>
                        <td><input type="text" id="y1"></td>
                    </tr>

                    <thead>
                    <th id="school_2"></th>
                    <th id="id_2"></th>
                    <th id="y_2"></th>
                    </thead>
                    <tr>
                        <td><input type="text" id="school2"></td>
                        <td><input type="text" id="id2"></td>
                        <td><input type="text" id="y2"></td>
                    </tr>
                    <thead>
                    <th id="school_3"></th>
                    <th id="id_3"></th>
                    <th id="y_3"></th>
                    </thead>
                    <tr>
                        <td><input type="text" id="school3"></td>
                        <td><input type="text" id="id3"></td>
                        <td><input type="text" id="y3"></td>
                    </tr>

                    <thead>
                    <th id="school_4"></th>
                    <th id="id_4"></th>
                    <th id="y_4"></th>
                    </thead>
                    <tr>
                        <td><input type="text" id="school4"></td>
                        <td><input type="text" id="id4"></td>
                        <td><input type="text" id="y4"></td>
                    </tr>

                    <thead>
                    <th id="school_5"></th>
                    <th id="id_5"></th>
                    <th id="y_5"></th>
                    </thead>
                    <tr>
                        <td><input type="text" id="school5"></td>
                        <td><input type="text" id="id5"></td>
                        <td><input type="text" id="y5"></td>
                    </tr>
                </table>
            </form>
            <div class="revise">
                <input type="submit" value="Revise" style="font-size: 18px;">
            </div>
        </div>
    </div>
    <div class="hero-image">
        <img src="images/hero-image.png" alt=""/>
    </div>
</section>
</body>
</html>
