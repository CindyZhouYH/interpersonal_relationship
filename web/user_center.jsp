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
            // get basic information
            $.ajax({
                type: 'post',
                url: "${pageContext.request.contextPath}/user/PersonalCenter/PersonalInfo",
                data: {},
                async: false,
                success: function (data) {
                    let msg = JSON.parse(data);
                    console.log(msg.username);
                    console.log(msg.name);
                    console.log(msg.email);
                    console.log(msg.key);
                    let uname = msg.username;
                    let name = msg.name;
                    let email = msg.email;
                    let key = msg.key;
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
            // get valid school information
            var i = 1;
            //alert("getting entrance info");
            $.ajax({
                type: 'post',
                url: "${pageContext.request.contextPath}/user/PersonalCenter/EntranceInfo",
                data: {},
                async: false,
                success: function (data) {
                    let msg = JSON.parse(data);
                    console.log("msg length");
                    console.log(msg.length);
                    if (msg.length > 0 && msg.length <= 3) {
                        document.getElementsByClassName("table2")[0].style.display = "block";
                    } else if (msg.length > 3) {
                        document.getElementsByClassName("table2")[0].style.display = "block";
                        document.getElementsByClassName("table3")[0].style.display = "block";
                    }
                    for (i = 1; i <= msg.length; i++) {
                        console.log("showing when getting user profile");
                        console.log("school" + i);
                        console.log(msg[i - 1].school_id);
                        //$("#tr_" + i + ", #delete_button_" + i).show();
                        document.getElementById("tr_" + i).style.display = "block";
                        document.getElementById("delete_button_" + i).style.display = "block";
                        document.getElementById("school" + i).value = msg[i - 1].schoolName;
                        document.getElementById("id" + i).value = msg[i - 1].id;
                        document.getElementById("y" + i).value = msg[i - 1].year;
                        //document.getElementById("school_" + i).innerHTML = "School" + i + ":";
                        document.getElementById("school_" + i).innerHTML = msg[i - 1].schoolType;
                        document.getElementById("id_" + i).innerHTML = "Identity:";
                        document.getElementById("y_" + i).innerHTML = "Year:";
                    }
                },
                error: function (data) {
                    alert("errorGettingSchool");
                    console.log(data);
                }
            });
        }
        function logOff() {
            let uname = "";
            let name = "";
            let email = "";
            let key = "";
            $.ajax({
                type: 'post',
                url: "${pageContext.request.contextPath}/user/PersonalCenter/PersonalInfo",
                data: {},
                async: false,
                success: function (data) {
                    let msg = JSON.parse(data);
                    uname = msg.username;
                    name = msg.name;
                    email = msg.email;
                    key = msg.key;
                },
                error: function (data) {
                    alert("error1");
                    console.log(data);
                }
            });
            $.ajax({
                type: 'post',
                url: "${pageContext.request.contextPath}/user/delete",
                data: {
                    "username": uname, "name": name,
                    "email": email, "key": key
                },
                success: function (data) {
                    //alert(data);
                    location.href = "index.jsp";
                },
                error: function (data) {
                    alert("error2");
                    console.log(data);
                }
            });
        }

        function addSchool() {
            var length = 0;
            $.ajax({
                type: 'post',
                url: "${pageContext.request.contextPath}/user/PersonalCenter/getSchoolNum",
                data: {},
                async: false,
                success: function (data) {
                    let msg = JSON.parse(data);
                    console.log(msg);
                    length = msg.length + 1;
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
            let index = document.getElementById("schools").selectedIndex;
            let value = document.getElementById("schools").options[index].value;
            $.ajax({
                type: 'post',
                url: "${pageContext.request.contextPath}/user/addSchool",
                data: {schoolName: '', level: value, year: '0'},
                async: false,
                success: function (data) {
                    console.log(data);
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
        }

        function deleteSchool(index) {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/deleteSchool',
                data: {index: index - 1},
                success: function(data) {
                    if (data == "1") {
                        console.log("删除成功");
                    } else {
                        alert("删除失败");
                    }
                },
                error: function(data) {
                    alert("error");
                    console.log(data);
                }
            })
        }

        function updateSchool(index) {
            let index2 = document.getElementById("schools").selectedIndex;
            let lvl = document.getElementById("schools").options[index2].value;
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/updateSchool',
                data: {
                    index: index - 1,
                    name: document.getElementById("school" + index).value,
                    year: document.getElementById("y" + index).value,
                    level: lvl
                },
                success: function(data) {
                    alert(data);
                    if (data == "11") {
                        console.log("更新成功");
                    } else {
                        alert("更新失败");
                    }
                },
                error: function(data) {
                    alert("error");
                    console.log(data);
                }
            })
        }
    </script>
</head>
<body onload="getUserProfile()">
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
    <ul class="header-social">
        <li>
            <button id="logoff" onclick="logOff()"><span>logOff</span></button>
        </li>
    </ul>
</header>
<section id="hero">
    <div class="content">
        <div class="panel">
            <div class="uc">User Center</div>
            <form action="${pageContext.request.contextPath}/user/updateInform" method="post">
                <table class="table1">
                    <thead>
                    <th>Username:</th>
                    <th>Password:</th>
                    <th>Name:</th>
                    <th>Email:</th>
                    <th>Add School:</th>
                    <th>
                        <button id="add_button" onclick="addSchool()">Add</button>
                    </th>
                    </thead>
                    <tr>
                        <td><input type="text" id="username" name="username"></td>
                        <td><input type="text" id="password" name="password"></td>
                        <td><input type="text" id="name" name="name"></td>
                        <td><input type="text" id="email" name="email"></td>
                        <td><select id="schools" name="level">
                            <option value="kd">Kindergarten</option>
                            <option value="ps">Primary School</option>
                            <option value="jhs">Junior High School</option>
                            <option value="shs">Senior High School</option>
                            <option value="uni">University</option>
                        </select></td>
                        <td>
                            <div class="revise">
                                <input type="submit" id="revise_button" value="Revise" style="font-size: 18px;">
                            </div>
                        </td>
                    </tr>
                </table>
                <table class="table2">
                    <thead>
                    <th id="school_1"></th>
                    <th id="id_1"></th>
                    <th id="y_1"></th>
                    <th>
                        <button id="delete_button_1" onclick="deleteSchool(1)">Delete</button>
                    </th>
                    </thead>
                    <tr id="tr_1" style="display: none">
                        <td><input type="text" id="school1" name="school1"></td>
                        <td><input type="text" id="id1" name="id1"></td>
                        <td><input type="text" id="y1" name="y1"></td>
                        <td>
                            <button id="revise_button_1" onclick="updateSchool(1)">Revise</button>
                        </td>
                    </tr>

                    <thead>
                    <th id="school_2"></th>
                    <th id="id_2"></th>
                    <th id="y_2"></th>
                    <th>
                        <button id="delete_button_2" onclick="deleteSchool(2)">Delete</button>
                    </th>
                    </thead>
                    <tr id="tr_2" style="display: none">
                        <td><input type="text" id="school2" name="school2"></td>
                        <td><input type="text" id="id2" name="id2"></td>
                        <td><input type="text" id="y2" name="y2"></td>
                        <td>
                            <button id="revise_button_2" onclick="updateSchool(2)">Revise</button>
                        </td>
                    </tr>
                    <thead>
                    <th id="school_3"></th>
                    <th id="id_3"></th>
                    <th id="y_3"></th>
                    <th>
                        <button id="delete_button_3" onclick="deleteSchool(3)">Delete</button>
                    </th>
                    </thead>
                    <tr id="tr_3" style="display: none">
                        <td><input type="text" id="school3" name="school3"></td>
                        <td><input type="text" id="id3" name="id3"></td>
                        <td><input type="text" id="y3" name="y3"></td>
                        <td>
                            <button id="revise_button_3" onclick="updateSchool(3)">Revise</button>
                        </td>
                    </tr>
                </table>
                <table class="table3">
                    <thead>
                    <th id="school_4"></th>
                    <th id="id_4"></th>
                    <th id="y_4"></th>
                    <th>
                        <button id="delete_button_4" onclick="deleteSchool(4)">Delete</button>
                    </th>
                    </thead>
                    <tr id="tr_4" style="display: none">
                        <td><input type="text" id="school4" name="school4"></td>
                        <td><input type="text" id="id4" name="id4"></td>
                        <td><input type="text" id="y4" name="y4"></td>
                        <td>
                            <button id="revise_button_4" onclick="updateSchool(4)">Revise</button>
                        </td>
                    </tr>

                    <thead>
                    <th id="school_5"></th>
                    <th id="id_5"></th>
                    <th id="y_5"></th>
                    <th>
                        <button id="delete_button_5" onclick="deleteSchool(5)">Delete</button>
                    </th>
                    </thead>
                    <tr id="tr_5" style="display: none">
                        <td><input type="text" id="school5" name="school5"></td>
                        <td><input type="text" id="id5" name="id5"></td>
                        <td><input type="text" id="y5" name="y5"></td>
                        <td>
                            <button id="revise_button_5" onclick="updateSchool(5)">Revise</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="hero-image">
            <img src="images/hero-image.png" alt=""/>
        </div>
    </div>
</section>
</body>
</html>