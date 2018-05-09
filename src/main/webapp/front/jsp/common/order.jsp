<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Order" scope="page" id="order"/>
<jsp:useBean class="epam.chernova.finalproject.entity.Product" scope="page" id="product"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.weight" var="weight"/>

<html>
<head>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/slider/2016/cfb/jquery.carousel.fullscreen.css"/>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/slider/2016/cfb/jquery.carousel.fullscreen.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.css"/>
    <script src="http://bootstraptema.ru/snippets/menu/2017/cdmenu/cdmenu.js"></script>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/form.js" %>
    </script>
    <style>
        <%@include file="/front/css/menu.css" %>
        <%@include file="/front/css/form.css" %>
        <%@include file="/front/css/common.css" %>
        <%@include file="/front/css/orderlist.css" %>
        <%@include file="/front/css/order.css" %>

    </style>
    <title>Cafe</title>
</head>
<header>
    <%@ include file="/front/jsp/common/header.jsp" %>
</header>

<body>
<%@ include file="/front/jsp/common/menu.jsp" %>
<%@ include file="/front/jsp/common/form.jsp" %>
<%@ include file="/front/jsp/common/information.jsp" %>
<ctg:orderlist/>
</body>
</html>