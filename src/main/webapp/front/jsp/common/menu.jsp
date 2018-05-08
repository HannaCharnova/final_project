<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.menu" var="menu"/>
<fmt:message bundle="${loc}" key="local.word.breakfast" var="breakfast"/>
<fmt:message bundle="${loc}" key="local.word.pizza" var="pizza"/>
<fmt:message bundle="${loc}" key="local.word.sushi" var="sushi"/>
<fmt:message bundle="${loc}" key="local.word.snacks" var="snacks"/>
<fmt:message bundle="${loc}" key="local.word.hot_dishes" var="hot_dishes"/>
<fmt:message bundle="${loc}" key="local.word.soup" var="soups"/>
<fmt:message bundle="${loc}" key="local.word.desserts" var="desserts"/>
<fmt:message bundle="${loc}" key="local.word.coffe" var="coffe"/>
<fmt:message bundle="${loc}" key="local.word.cocoa" var="cocoa"/>
<fmt:message bundle="${loc}" key="local.word.tea" var="tea"/>
<fmt:message bundle="${loc}" key="local.word.smuzzi" var="smuzzi"/>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.js"></script>
    <style>
        <%@include file="/front/css/menu.css" %>
    </style>

</head>
<body>

<section class="cd-section">
    <button class="cd-bouncy-nav-trigger">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
</section>

<div class="cd-bouncy-nav-modal">
    <nav>
        <ul class="cd-bouncy-nav">
            <li><a href="/epam.by/find_by_type?product_type=breakfast">${breakfast}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=pizza">${pizza}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=sushi">${sushi}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=snacks">${snacks}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=soups">${soups}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=desserts">${desserts}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=coffe">${coffe}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=cocoa">${cocoa}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=tea">${tea}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=hot_dishes">${hot_dishes}</a></li>
            <li><a href="/epam.by/find_by_type?product_type=smuzzi">${smuzzi}</a></li>
        </ul>
    </nav>
    <a href="#0" class="cd-close">Close modal</a>
</div>

</body>
</html>
