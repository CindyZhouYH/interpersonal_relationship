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
    <script>
        function onld()
        {
            document.getElementById("school1").innerHTML="ABC";
            $.ajax({
                type:"POST",
                //url:"${pageContext.request.contextPath}/user/PersonalCenter.form",
                cache: false,
                dataType: 'json',
                success:function(msg){
                    //var user=msg[0];
                    //var num =msg[0].num;
                    var username = msg[0].username;
                    var name = msg[0].name;
                    var email = msg[0].email;
                    var key= msg[0].key;
                    document.getElementById(username).value = ${username};
                    document.getElementById(name).value = ${name};
                    document.getElementById(password).vlaue = ${key};
                    document.getElementById(email).value = ${email};
                },
                error:function(){
                    alert("error");
                }
            });
        }
    </script>
</head>
<body onload="onld()">
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
        <li><a href="login.html" style="text-decoration: none;"><span>logOff</span></a></li>
    </ul>
</header>
<section id="hero">
    <div class = "content">
        <div class = "panel">
            <div class="uc">User Center</div>
            <table class = "table1" >
                <thead>
                <th>Username:</th>
                <th>Password:</th>
                <th>Name:</th>
                <th>Email:</th>
                </thead>
                <tr>
                    <td><input type = "text" id = "username" value =${msg}></td>
                    <td><input type = "text" id = "password" value =key></td>
                    <td><input type = "text" id = "name" value =name></td>
                    <td><input type = "text" id = "email" value =email></td>
                </tr>
                <thead>
                <th id = "school1"></th>
                <th id = "id1"></th>
                <th id = "y1"></th>
                </thead>
                <tr>
                    <td><input type = "text" id = "school1" value =""></td>
                    <td><input type = "text" id = "id1" value =""></td>
                    <td><input type = "text" id = "y1" value =""></td>
                </tr>

                <thead>
                <th id = "school2"></th>
                <th id = "id2"></th>
                <th id = "y2"></th>
                </thead>
                <tr>
                    <td><input type = "text" id = "school2" value ="111"></td>
                    <td><input type = "text" id = "id2" value ="2222"></td>
                    <td><input type = "text" id = "y2" value ="333"></td>
                </tr>
                <thead>
                <th id = "school3"></th>
                <th id = "id3"></th>
                <th id = "y3"></th>
                </thead>
                <tr>
                    <td><input type = "text" id = "school3" value ="111"></td>
                    <td><input type = "text" id = "id3" value ="2222"></td>
                    <td><input type = "text" id = "y3" value ="333"></td>
                </tr>

                <thead>
                <th id = "school4"></th>
                <th id = "id4"></th>
                <th id = "y4"></th>
                </thead>
                <tr>
                    <td><input type = "text" id = "school4" value ="111"></td>
                    <td><input type = "text" id = "id4" value ="2222"></td>
                    <td><input type = "text" id = "y4" value ="333"></td>
                </tr>

                <thead>
                <th id = "school5"></th>
                <th id = "id5"></th>
                <th id = "y5"></th>
                </thead>
                <tr>
                    <td><input type = "text" id = "school5" value ="111"></td>
                    <td><input type = "text" id = "id5" value ="2222"></td>
                    <td><input type = "text" id = "y5" value ="333"></td>
                </tr>

        </div>
        </table>
    </div>
    </div>
    <div class="hero-image">
        <img src="images/hero-image.png" alt="" />
    </div>
</section>
</body>
</html>
