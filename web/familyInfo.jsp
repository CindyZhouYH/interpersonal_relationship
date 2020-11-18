<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>familyInfo</title>
    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/register_layout.css">
    <link rel="stylesheet" href="css/register.css">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
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
        </ul>
    </nav>
</header>
<div class="content">
    <div class="panel">
        <form action="${pageContext.request.contextPath}/birthinfo/addBirthInfo.form" method="post">
            <div class="re">Family Information</div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Father's Family Name: <input type="text" id="father_name" name="father_name">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Father's Roots: <input type="text" id="father_place" name="father_place">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                           Mather's Family Name: <input type="text" id="mother_name" name="mother_name">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Mother's Roots: <input type="text" id="mother_place" name="mother_place">
                    </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class="register_w">
                            Birth: <input type="text" id="year" name="year">
                    </span>
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
