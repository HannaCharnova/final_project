<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.sentence.main_text_first" var="main_text_first"/>
<fmt:message bundle="${loc}" key="local.sentence.main_text_second" var="main_text_second"/>

<head>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
    </script>
</head>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="/front/image/main_first.jpg" alt=""/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>LOUNGE CAFE</h1>
                    <p class="lead">${main_text_first}</p>
                </div>
            </div>
        </div>

        <div class="item">
            <img src="/front/image/main_second.jpg" alt=""/>
            <div class="container">
                <div class="carousel-caption">
                    <h1>LOUNGE CAFE</h1>
                    <p class="lead">${main_text_second}</p>
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
