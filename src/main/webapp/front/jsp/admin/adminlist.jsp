<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.ext.Administrator" scope="page" id="adminuser"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.main_admin" var="main_admin"/>
<fmt:message bundle="${loc}" key="local.word.delete_admin_word" var="delete_admin_word"/>
<fmt:message bundle="${loc}" key="local.word.add_admin_word" var="add_admin_word"/>

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
    <c:choose>
        <c:when test="${admin.main eq 'true'}">
            <button type="submit" id="add-admin-button" class="btn btn-default"><a id="addadmin">${add_admin_word}</a></button>
        </c:when>
    </c:choose>

    <div class="qa-message-list">

        <c:choose>
            <c:when test="${admins!=null}">
                <c:forEach var="adminuser" items="${admins}">
                    <c:if test="${adminuser.idUser!=admin.idUser}">

                        <div class="message-item">
                            <div class="message-inner">
                                <div class="clearfix">
                                    <form action="/epam.by/delete_admin?idAdmin=${adminuser.idUser}" method="post">

                                        <div class="user-detail">
                                            <c:choose>
                                                <c:when test="${admin.main eq 'true'}">
                                                    <button type="submit" id="delete-admin-button" class="btn-right"
                                                            class="btn btn-default">${delete_admin_word}</button>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${adminuser.main eq 'true'}">
                                                    <h5 class="handle">${main_admin}</h5>
                                                </c:when>
                                            </c:choose>
                                            <br>
                                            <h5 class="handle">${adminuser.login} </h5>


                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->
</div><!-- ./container -->
</body>
</html>