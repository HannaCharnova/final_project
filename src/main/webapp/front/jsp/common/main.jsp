<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.word.sign_up" var="sign_up"/>
<fmt:message bundle="${loc}" key="local.word.hello" var="hello"/>


<body>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/slider/2016/cfb/jquery.carousel.fullscreen.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/slider/2016/cfb/jquery.carousel.fullscreen.js"></script>
    <style>

    </style>
</head>
<body>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="http://drawings-girls.ucoz.net/2016/11/devushka-lucefera.jpg" alt=""/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>Bootstrap Carousel Fullscreen</h1>
                    <p class="lead">Just turns the Twitter Bootstrap Carousel in fullscreen mode, and scale to fit the
                        screen resolution</p>
                </div>
            </div>
        </div>

        <div class="item">
            <img src="http://drawings-girls.ucoz.net/2016/11/devochka-i-chujoi.jpg" alt=""/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>Bootstrap Carousel Fullscreen</h1>
                    <p class="lead">Just turns the Twitter Bootstrap Carousel in fullscreen mode, and scale to fit the
                        screen resolution</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>
</div>
</body>

</html>