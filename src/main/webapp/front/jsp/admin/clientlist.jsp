<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.ext.Client" scope="page" id="client"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.email" var="email"/>
<fmt:message bundle="${loc}" key="local.word.status_word" var="status_word"/>
<fmt:message bundle="${loc}" key="local.word.not_banned" var="not_banned"/>
<fmt:message bundle="${loc}" key="local.word.banned" var="banned"/>
<fmt:message bundle="${loc}" key="local.word.ban_client" var="ban_client"/>
<fmt:message bundle="${loc}" key="local.word.unban_client" var="unban_client"/>

<head>
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


<div class="order-container">

    <div class="qa-message-list">

        <c:choose>
            <c:when test="${clients!=null}">
                <c:forEach var="client" items="${clients}">
                    <div class="message-item">
                        <div class="message-inner">
                            <div class="clearfix">
                                <form action="/epam.by/ban_client?idClient=${client.iduser}" method="post">

                                    <div class="user-detail">
                                        <h5 class="handle">${client.name} ${client.surname}</h5>
                                        <c:choose>
                                            <c:when test="${client.ban eq 'false'}">
                                                <button type="submit" class="btn-right"
                                                        class="btn btn-default">${ban_client}</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" disabled="disabled" class="btn-right"
                                                        class="btn btn-default">${unban_client}</button>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="post-meta">
                                            <div class="asker-meta">
                                                <span class="qa-message-when-data">${login}: ${client.login}</span><br>
                                                <span class="qa-message-when-data">${email}: ${client.email} </span><br>
                                                <c:choose>
                                                    <c:when test="${client.ban eq 'false'}">
                                                        <span class="qa-message-when-data">${status_word}: ${not_banned}</span>
                                                    </c:when>
                                                    <c:when test="${client.ban eq 'true'}">
                                                        <span class="qa-message-when-data">${status_word}: ${banned}</span>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->
</div><!-- ./container -->
</body>
</html>