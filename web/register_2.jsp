<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>register_2</title>
    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/register_layout.css">
    <link rel="stylesheet" href="css/register.css">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
    </script>
    <script type="text/javascript">
        duonload = function(){
            var html1 = $("#fm1").html();
            var html2 = $("#fm2").html();
            var html3 = $("#fm3").html();
            var html4 = $("#fm4").html();
            var html5 = $("#fm5").html();
            $("#show1").html("");
            $("#show2").html("");
            $("#show3").html("");
            $("#show4").html("");
            $("#show5").html("");
            //    $("#show").html(html1);
            var index=document.getElementById("schools").selectedIndex;//获取当前选择项的索引.
            var value = document.getElementById("schools").options[index].value;
            if(value=="1"){
                $("#show1").html(html1);
            }else if(value=="2"){
                $("#show1").html(html1);
                $("#show2").html(html2);
            }else if(value=="3"){
                $("#show1").html(html1);
                $("#show2").html(html2);
                $("#show3").html(html3);
            }else if(value=="4"){
                $("#show1").html(html1);
                $("#show2").html(html2);
                $("#show3").html(html3);
                $("#show4").html(html4);
            }else if(value=="5"){
                $("#show1").html(html1);
                $("#show2").html(html2);
                $("#show3").html(html3);
                $("#show4").html(html4);
                $("#show5").html(html5);
            }
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
<div class = "content">
    <div class = "panel">
        <div class="re">Registration</div>
        <div class="group">
            <div class="star">
                *
                <span class = "register_w">
                        School Num: <select id="schools">
                            <option value="0">0</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            </select>
                    </span>
                <button onclick="duonload()">Confirm</button>
            </div>
        </div>
        <form action="${pageContext.request.contextPath}/registerEntranceInfo/add.form" method="post">
            <div id="show1"style="display:inline;"></div>
            <div id="show2"style="display:inline;"></div>
            <div id="show3"style="display:inline;"></div>
            <div id="show4"style="display:inline;"></div>
            <div id="show5"style="display:inline;"></div>
            <div class="login">
                <input type="submit" value="Submit" style="font-size: 18px;">
            </div>
        </form>

        <form id = "fm1" style="display: none;">
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            SchoolName1: <input type="text" name="schoolName1" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Identity: <input type="text" name="level1" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Year: <input type="text" name="year1" type="text">
                        </span>
                </div>
            </div>
        </form>

        <form id = "fm2" style="display: none;">
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            SchoolName2: <input type="text" name="schoolName2" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Identity: <input type="text" name="level2" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Year: <input type="text" name="year2" type="text">
                        </span>
                </div>
            </div>
        </form>

        <form id = "fm3" style="display: none;">
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            SchoolName3: <input type="text" name="schoolName3" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Identity: <input type="text" name="level3" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Year: <input type="text" name="year3" type="text">
                        </span>
                </div>
            </div>
        </form>

        <form id = "fm4" style="display: none;">
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            SchoolName4: <input type="text" name="schoolName4" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Identity: <input type="text" name="level4" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Year: <input type="text" name="year4" type="text">
                        </span>
                </div>
            </div>
        </form>

        <form id = "fm5" style="display: none;">
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            SchoolName5: <input type="text" name="schoolName5" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Identity: <input type="text" name="level5" type="text">
                        </span>
                </div>
            </div>
            <div class="group">
                <div class="star">
                    *
                    <span class = "register_w">
                            Year: <input type="text" name="year5" type="text">
                        </span>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>