<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>Connection</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="css/default.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/media-queries.css">

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/echarts.js"></script>
    <script src="js/modernizr.js"></script>
    <script>
        function judgeState() {
            //document.getElementById("usercenter_state").innerHTML = "UserCenter";
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/checkLoginState",
                data: {},
                success: function (data) {
                    var msg = JSON.parse(data);
                    console.log(msg.state);
                    var state = msg.state;
                    if (state == "1") {
                        document.getElementById("usercenter_state").innerHTML = "UserCenter";
                    } else {
                        document.getElementById("login_state").innerHTML = "login";
                        document.getElementById("register_state").innerHTML = "register";
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
        function getResult() {
            var myChart = echarts.init(document.getElementById('main'));
            var brower = [];
            var link = [];
            var categories = [];
            categories[0] = {
                name: "SearchEnds"
            };
            categories[1] = {
                name: "Connection"
            };
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/relations/getall",
                async: false,
                dataType: 'json',
                data: {
                    'name': document.getElementById("search_name").value
                },

                success: function (msg) {
                    console.log(msg);
                    for (i = 1; i <= msg.length; i++) {
                        var cate = 0
                        var size = 0
                        if (msg[i - 1].user.name === document.getElementById("search_name").value ||
                            msg[i - 1].user.name === "<%=session.getAttribute("user_name")%>") {
                            cate = 0
                            size = 100
                        } else {
                            cate = 1
                            size = 70
                        }
                        brower[i - 1] = {
                            name: msg[i - 1].user.name,
                            des: msg[i - 1].user.name,
                            symbolSize: size / Math.log(msg.length + 1),
                            category: cate
                        };
                    }
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/relations/search",
                data: {},
                async: false,
                success: function (data) {
                    let msg = JSON.parse(data);
                    console.log(msg);
                    for (let i = 1; i <= msg.length; i++) {
                        link[i - 1] = {
                            source: msg[i - 1].user_1.name,
                            target: msg[i - 1].user_2.name,
                            name: msg[i - 1].type,
                            des: msg[i - 1].type
                        };
                    }
                },
                error: function (data) {
                    alert("error");
                    console.log(data);
                }
            });
            option = {
                tooltip: {
                    formatter: function (x) {
                        return x.data.des;
                    }
                },
                // 工具箱
                toolbox: {
                    // 显示工具箱
                    show: true,
                    feature: {
                        mark: {
                            show: true
                        },
                        // 还原
                        restore: {
                            show: true
                        },
                        // 保存为图片
                        saveAsImage: {
                            show: true
                        }
                    }
                },
                legend: [{
                    // selectedMode: 'single',
                    data: categories.map(function (a) {
                        return a.name;
                    })
                }],
                series: [{
                    type: 'graph', // 类型:关系图
                    layout: 'force', //图的布局，类型为力导图
                    symbolSize: 40, // 调整节点的大小
                    //roam: true, // 是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移,可以设置成 'scale' 或者 'move'。设置成 true 为都开启
                    edgeSymbol: ['circle', 'arrow'],
                    edgeSymbolSize: [2, 10],
                    edgeLabel: {
                        normal: {
                            textStyle: {
                                fontSize: 20 / Math.log(brower.length)
                            }
                        }
                    },
                    force: {
                        repulsion: 3000 / Math.log(brower.length),
                        edgeLength: [5, 10]
                    },
                    draggable: true,
                    lineStyle: {
                        normal: {
                            width: 2,
                            color: '#4b565b',
                        }
                    },
                    edgeLabel: {
                        normal: {
                            show: true,
                            formatter: function (x) {
                                return x.data.name;
                            }
                        }
                    },
                    label: {
                        normal: {
                            show: true,
                            textStyle: {}
                        }
                    },

                    // 数据
                    data: brower,
                    links: link,
                    categories: categories,
                }]
            };
            myChart.setOption(option);
            document.getElementById("main").style.display = "block";
            myChart.on('click', function (params) {
                document.getElementById("add_text").value = params.name;
            })
        }
    </script>
</head>
<body onload="judgeState()">
<header>

    <div class="logo">
        <a class="smoothscroll" href="#hero"><img alt="" src="images/logo_1.png"></a>
    </div>

    <nav id="nav-wrap">

        <a class="mobile-btn" href="#nav-wrap" title="Show navigation">Show Menu</a>
        <a class="mobile-btn" href="#" title="Hide navigation">Hide Menu</a>

        <ul id="nav" class="nav">
            <li><a class="smoothscroll" href="#trial">Trial</a></li>
            <li><a class="smoothscroll" href="#testimonials">Introduction</a></li>
            <li><a href="user_center.jsp" id="usercenter_state"></a></li>
        </ul>

    </nav>

    <ul class="header-social">
        <li><a href="login.jsp"><span id="login_state"></span></a></li>
        <li><a href="register_1.jsp"><span id="register_state"></span></a></li>
        <li><a href="register_2.jsp"><span id="school_state"></span></a></li>
    </ul>

</header>
<section id="hero">

    <div class="row">
        <div class="twelve columns">
            <div class="hero-text">
                <h1 class="responsive-headline">The perfect platform about Six Degrees of Separation.</h1>

                <p>You can search the relationship with strangers as long as he/she is a registered user of our website.
                    We will show you the interpersonal
                    relationship in a visual way of network diagram. You can also add the people in the network diagram
                    if you want. Come and have a free trial !</p>

            </div>

            <div class="buttons">
                <a class="button trial smoothscroll" href="#trial">Free Trial</a>
                <a class="button learn-more smoothscroll" href="#testimonials">Learn More</a>
            </div>

            <div class="hero-image">
                <img src="images/hero-image.png" alt=""/>
            </div>


        </div>

    </div>

</section> <!-- Homepage Hero end -->
<section id='trial'>
    <div class="six columns left">
        <%--  <form action="">--%>
        <table>
            <td>
                <button style="font-size: 20px">Username:</button>
            </td>
            <td><input id="search_name" type="text"></td>
        </table>
        <button onclick="getResult()" style="outline:none">Get Your Result</button>
        <%--  </form>--%>
    </div>
    <div class="row feature design">

        <div class="six columns right" >
            <h2>instruction</h2>

            <p>Please enter a username then click "GET YOUR RESULT" button.
            </p>
        </div>
        <div class="six columns feature-media left">
            <%--      <img src="images/gragh.png" alt=""/>--%>
            <div id="main" style="width: 850px;height:700px;padding-top:10px;display:none"></div>
            <div class="six columns left">
                <table>
                    <form action="${pageContext.request.contextPath}/friend/addFriend" method="post">
                        <td id="add_button"><input type="submit" value="Add" style="height: 50px "></td>
                        <td><input id="add_text" name="name" type="text"></td>
                    </form>
                </table>
            </div>
        </div>
</section>

<section id="testimonials">
    <div class="row content">
        <span><i class="quote-left fa fa-quote-left"></i></span>
        <div class="text-container">
            <div class="twelve columns flex-container">
                <div class="flexslider">
                    <ul class="slides">
                        <li>
                            <blockquote>
                                <p>Six Degrees of Separation</p>
                                <p>There are no more than five people between you and any stranger, that is, you can
                                    meet any stranger through at most five people!

                                </p>
                                <cite>Stanley Milgram</cite>
                            </blockquote>
                        </li>
                        <li>
                            <blockquote>
                                <p>According to this concept, we implement this interpersonal relationship query
                                    website. As long as the person you are inquiring is a registered user of this
                                    website, we will try to find the relationship between you two, and present it in a
                                    visual way of network diagram.
                                </p>
                            </blockquote>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <span><i class="quote-right fa fa-quote-right"></i></span>
    </div>
</section>

<footer>
    <div class="row">
        <div id="go-top">
            <a class="smoothscroll" title="Back to Top" href="#hero"><i class="icon-up-open"></i></a>
        </div>
    </div>
</footer>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.10.2.min.js"><\/script>')</script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>

<script src="js/jquery.flexslider.js"></script>
<script src="js/waypoints.js"></script>
<script src="js/jquery.fittext.js"></script>
<script src="js/jquery.fitvids.js"></script>
<script src="js/imagelightbox.js"></script>
<script src="js/jquery.prettyPhoto.js"></script>
<script src="js/main.js"></script>

</body>
</html>