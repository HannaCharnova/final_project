<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.name" var="name"/>
<fmt:message bundle="${loc}" key="local.word.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.word.email" var="email"/>
<fmt:message bundle="${loc}" key="local.word.login" var="login"/>
<fmt:message bundle="${loc}" key="local.word.password" var="password"/>
<fmt:message bundle="${loc}" key="local.sentence.password_mistake" var="password_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.name_mistake" var="name_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.surname_mistake" var="surname_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.email_mistake" var="email_mistake"/>


<html>
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
    <script src="http://bootstraptema.ru/plugins/2016/validator/validator.min.js"></script>
    <meta charset=utf-8"/>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/form.js" %>
    </script>
    <style>
        <%@include file="/front/css/menu.css" %>
        <%@include file="/front/css/form.css" %>
        <%@include file="/front/css/common.css" %>
        <%@include file="/front/css/product.css" %>

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


<%--<div class="container">--%>
<div id="profile-row">
    <div class="col-md-6 col-md-offset-3">

        <form data-toggle="validator" role="form" id="profile-form">

            <div class="form-group">
                <label for="profileLogin" class="control-label">${login}</label>
                <input type="text" class="form-control" id="profileLogin" disabled="disabled" value="${client.login}" name="profileLogin"
                       required>
            </div>

            <div class="form-group">
                <label for="profileName" class="control-label">${name}</label>
                <input type="text" class="form-control" id="profileName" value="${client.name}" name="profileName"
                       required>
                <span class="profile-error-message" id="profile-name-span">${password_mistake}</span>

            </div>

            <div class="form-group">
                <label for="profileSurname" class="control-label">${surname}</label>
                <input type="text" class="form-control" id="profileSurname" value="${client.surname}"
                       name="profileSurname" required>
                <span class="profile-error-message" id="profile-surname-span">${password_mistake}</span>

            </div>

            <div class="form-group">
                <label for="profileEmail" class="control-label">${email}</label>
                <input type="email" class="form-control" id="profileEmail" value="${client.email}"
                       data-error="Вы не правильно ввели Ваш E-mail" name="profileEmail" required>
                <span class="profile-error-message" id="profile-email-span">${password_mistake}</span>
            </div>

            <div class="form-group">
                <label for="profilePassword" class="control-label">${password}</label>
                <div class="form-inline row" id="profile-password">
                    <input type="password" data-toggle="validator" data-minlength="6" class="form-control"
                           id="profilePassword" placeholder="123456" required>
                    <span class="profile-error-message" id="profile-password-span">${password_mistake}</span>
                    <input type="password" class="form-control" id="inputPasswordConfirm"
                           data-match="#profilePassword" data-match-error="Ошибка! Пароли не совпадают!"
                           placeholder="Повторите пароль" name="profilePasswordConfirm" required style="margin-top: 15px">
                    <span class="profile-error-message" id="profile-re-password-span">${password_mistake}</span>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Отправить</button>
            </div>
        </form>

    </div>
</div>
<%--</div>--%>


</body>
</html>
