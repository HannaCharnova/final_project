<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.menu" var="menu"/>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.css" />
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.js"></script>
    <style>
        <%@include file="/front/css/common.css" %>
    </style>

</head>
<body>

<section class="cd-section">
    <button class="cd-bouncy-nav-trigger" >
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
</section>

<div class="cd-bouncy-nav-modal">
    <nav>
        <ul class="cd-bouncy-nav">
            <li><a href="/epam.by/product_list">${menu}</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#services">Services</a></li>
            <li><a href="#gallery">Gallery</a></li>
            <li><a href="#team">Our Team</a></li>
            <li><a href="#contact">Contact</a></li>
        </ul>
    </nav>
    <a href="#0" class="cd-close">Close modal</a>
</div>

</body>
</html>
