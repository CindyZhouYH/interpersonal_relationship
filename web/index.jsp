<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>Connection</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile Specific Metas
  ================================================== -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

  <!-- CSS
  ================================================== -->
  <link rel="stylesheet" href="css/default.css">
  <link rel="stylesheet" href="css/layout.css">
  <link rel="stylesheet" href="css/media-queries.css">
  <link rel="stylesheet" href="css/animate.css">
  <link rel="stylesheet" href="css/prettyPhoto.css">

  <!-- Script
  ================================================== -->
  <script src="js/modernizr.js"></script>
  <script src="js/jquery-3.1.1.min.js"></script>
    <script >
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
                    if(state=="1"){
                        document.getElementById("usercenter_state").innerHTML = "UserCenter";
                    }else{
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
</head>
<body onload="judgeState()">
<%--<div id="preloader">--%>
<%--  <div id="status">--%>
<%--    <img src="images/preloader.gif" height="64" width="64" alt="">--%>
<%--  </div>--%>
<%--</div>--%>

<!-- Header
================================================== -->
<header>

  <div class="logo">
    <a class="smoothscroll" href="#hero"><img alt="" src="images/logo_1.png"></a>
  </div>

  <nav id="nav-wrap">

    <a class="mobile-btn" href="#nav-wrap" title="Show navigation">Show Menu</a>
    <a class="mobile-btn" href="#" title="Hide navigation">Hide Menu</a>

    <ul id="nav" class="nav">
      <li><a class="smoothscroll" href="#trial">Trial</a></li>
      <li><a class="smoothscroll" href="#screenshots">Screenshots</a></li>
      <li><a class="smoothscroll" href="#testimonials">Introduction</a></li>
      <li><a class="smoothscroll" href="#subscribe">Subscribe</a></li>
      <li><a href="user_center.jsp" id = "usercenter_state"></a></li>
    </ul> <!-- end #nav -->

  </nav> <!-- end #nav-wrap -->

  <ul class="header-social">
    <li><a href="login.jsp"><span id = "login_state"></span></a></li>
    <li><a href="register_1.jsp"><span id = "register_state"></span></a></li>
  </ul>

</header> <!-- Header End -->
<section id="hero">

  <div class="row">
    <div class="twelve columns">
      <div class="hero-text">
        <h1 class="responsive-headline">The perfect template to showcase your awesome product and service.</h1>

        <p>Aenean condimentum, lacus sit amet luctus lobortis, dolores et quas molestias excepturi
          enim tellus ultrices elit, amet consequat enim elit noneas sit amet luctu.
          Quis nostrum exercitationem ullam corporis suscipit laboriosam.</p>
      </div>

      <div class="buttons">
        <a class="button trial smoothscroll" href="#trial">Free Trial</a>
        <a class="button learn-more smoothscroll" href="#testimonials">Learn More</a>
      </div>

      <div class="hero-image">
        <img src="images/hero-image.png" alt="" />
      </div>

    </div>

  </div>

</section> <!-- Homepage Hero end -->
<section id='trial'>
  <button onclick="">Get Your Result</button>

  <div class="row feature design">

    <div class="six columns right">
      <h3>Simple, Clean and Modern Design.</h3>

      <p>Lorem ipsum dolor sit amet, ea eum labitur scripserit, illum complectitur deterruisset at pro. Odio quaeque reformidans est eu, expetendis intellegebat has ut, viderer invenire ut his. Has molestie percipit an. Falli volumus efficiantur sed id, ad vel noster propriae. Ius ut etiam vivendo, graeci iudicabit constituto at mea. No soleat fabulas prodesset vel, ut quo solum dicunt.
        Nec et amet vidisse mentitum. Cibo mutat nulla ei eam.
      </p>
    </div>

    <div class="six columns feature-media left">
      <img src="images/feature-image-1.png" alt="" />
    </div>

  </div>


  </div>

</section> <!-- Homepage Hero end -->

<!-- Screenshots
================================================== -->
<section id="screenshots">

  <div class="row section-head">

    <h1>Product Screenshots.</h1>

    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam,
      eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam
      voluptatem quia voluptas sit aspernatur aut odit aut fugit.
    </p>

  </div>

  <div class="row">

    <div class="twelve columns">

      <!-- screenshots-wrapper -->
      <div id="screenshots-wrapper" class="bgrid-quarters s-bgrid-thirds cf">

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-line-icons.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/line-icons.jpg" alt="Line Icons">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-hipster.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/hipster.jpg" alt="Hipster">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-authentic-vintage.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/authentic-vintage.jpg" alt="Authentic Vintage">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-spot-uv-logo.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/spot-uv-logo.jpg" alt="Spot UV Logo">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-tshirt-mockup.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/tshirt-mockup.jpg" alt="TShirt Mockup">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-abstract-vector.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/abstract-vector.jpg" alt="Abstract Vector">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-embossed-paper.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/embossed-paper.jpg" alt="Embossed Paper">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div> <!-- item end -->

        <div class="columns item">
          <div class="item-wrap">

            <a href="images/screenshots/big/b-judah.jpg" data-imagelightbox="a" >
              <img src="images/screenshots/judah.jpg" alt="Judah">
              <div class="overlay"></div>
              <div class="link-icon"><i class="icon-plus"></i></div>
            </a>

          </div>
        </div>  <!-- item end -->

      </div> <!-- portfolio-wrapper end -->

    </div> <!-- twelve columns end -->

  </div>  <!-- end row -->

</section> <!-- Screenshots End -->


<!-- Testimonials Section
================================================== -->
<section id="testimonials">
  <div class="row content">
    <span><i class="quote-left fa fa-quote-left"></i></span>
    <div class="text-container">
      <div class="twelve columns flex-container">
        <div class="flexslider">
          <ul class="slides">
            <li>
              <blockquote>
                <p>Your work is going to fill a large part of your life, and the only way to be truly satisfied is
                  to do what you believe is great work. And the only way to do great work is to love what you do.
                  If you haven't found it yet, keep looking. Don't settle. As with all matters of the heart, you'll know when you find it.
                </p>
                <cite>Steve Jobs</cite>
              </blockquote>
            </li> <!-- slide ends -->
            <li>
              <blockquote>
                <p>This is Photoshop's version  of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet.
                  Aenean sollicitudin, lorem quis bibendum auctor, nisi elit consequat ipsum, nec sagittis sem
                  nibh id elit. Duis sed odio sit amet nibh vulputate cursus a sit amet mauris.
                </p>
                <cite>Mr. Adobe</cite>
              </blockquote>
            </li> <!-- slide ends -->
          </ul>
        </div> <!-- div.flexslider ends -->
      </div> <!-- div.flex-container ends -->
    </div>  <!-- text-container ends -->
    <span><i class="quote-right fa fa-quote-right"></i></span>
  </div> <!-- row ends -->
</section> <!-- Testimonials Section End-->


<!-- Subscribe
================================================== -->
<section id="subscribe">

  <div class="row section-head">

    <div class="twelve columns">

      <h1>MailChimp signup form.</h1>

      <p>Adding your own MailChimp powered email sign-up is easy.
        Grab the super slim code from your MailChimp account and drop the code here. Lastly, remove the link and style tags
        that comes with the embedded code and your good to go. All styling is within our stylesheet.</p>

    </div>

  </div>

  <div class="row">

    <div class="twelve columns">

      <!-- Begin MailChimp Signup Form -->

      <div id="mc_embed_signup">

      </div>

      <p><small>We never share your information or use it to spam you.</small></p>

    </div>

  </div>

</section>  <!-- Subscribe Section End-->


<!-- Footer
================================================== -->
<footer>

  <div class="row">
    <div id="go-top">
      <a class="smoothscroll" title="Back to Top" href="#hero"><i class="icon-up-open"></i></a>
    </div>

  </div> <!-- Row End -->

</footer> <!-- Footer End-->


<!-- Java Script
================================================== -->
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