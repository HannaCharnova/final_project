<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.word.sign_up" var="sign_up"/>
<fmt:message bundle="${loc}" key="local.word.my_profile" var="my_profile"/>
<fmt:message bundle="${loc}" key="local.word.my_basket" var="my_basket"/>
<fmt:message bundle="${loc}" key="local.word.my_orders" var="my_orders"/>
<fmt:message bundle="${loc}" key="local.word.sign_out" var="sign_out"/>
<fmt:message bundle="${loc}" key="local.word.home" var="home"/>


<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.js"></script>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <title>Cafe</title>
    <style>
        <%@include file="/front/css/common.css" %>
        <%@include file="/front/css/form.css" %>
    </style>
    <script>
        <%@include file="/front/js/form.js" %>
    </script>
</head>

<div class="navbar navbar-my navbar-fixed-top" role="navigation" id="slide-nav">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-toggle">
                <span class="sr-only">Открыть меню</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
        </div>

        <div id="slidemenu">

            <c:choose>
                <c:when test="${role==1}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false"><img class="img-circle"
                                                          src="http://bootstraptema.ru/snippets/icons/2016/mia/1.png"
                                                          alt="USER" width="20"/> ${admin.login} <span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">My profile</a></li>
                                <li><a href="#">Favorited</a></li>
                                <li><a href="#">Settings</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Logout</a></li>
                            </ul>
                    </ul>
                </c:when>
                <c:when test="${role==2}">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false"><img class="img-circle"
                                                          src="http://bootstraptema.ru/snippets/icons/2016/mia/1.png"
                                                          alt="USER" width="20"/> ${client.login} <span
                                    class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">${my_profile}</a></li>
                                <li><a href="#">${my_orders}</a></li>
                                <li class="divider"></li>
                                <li><a href="/epam.by/sign_out">${sign_out}</a></li>
                            </ul>
                    </ul>
                </c:when>
                <c:otherwise>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a id="signin">${sign_in}</a></li>
                        <li><a id="signup">${sign_up}</a></li>
                    </ul>
                    <c:choose>
                        <c:when test="${error_data!=null}">
                            <script>
                                <%@include file="/front/js/information.js" %>
                            </script>
                        </c:when>
                    </c:choose>
                </c:otherwise>
            </c:choose>

            <ul class="nav navbar-nav">
                <li>
                    <form method="post" action="/epam.by/change_locale" id="locale">
                        <c:choose>
                            <c:when test="${locale eq 'ru'}">
                                <input class="img-circle" type="submit" id="lang_en">
                            </c:when>
                            <c:when test="${locale eq 'en'}">
                                <input class="img-circle" type="submit" id="lang_ru">
                            </c:when>
                        </c:choose>

                    </form>

                </li>
                <li><a href="/epam.by/index">${home}</a></li>
                <li><a href="http://bootstraptema.ru/stuff/templates_bootstrap/1">Шаблоны</a></li>
                <li><a href="http://bootstraptema.ru/stuff/plugins_bootstrap/2">Плагины</a></li>
                <li><a href="http://bootstraptema.ru/stuff/snippets_bootstrap/3">Сниппеты</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Ещё <b
                        class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="http://bootstraptema.ru/stuff/0-0-0-0-1">Заработать</a></li>
                        <li><a href="http://bootstraptema.ru/index/best_for_bootstrap/0-4">Топ 100</a></li>
                        <li><a href="http://bootstraptema.ru/index/feedback/0-3">Связь</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</div>


</html>