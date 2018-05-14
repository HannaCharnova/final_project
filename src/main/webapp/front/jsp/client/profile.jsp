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
<fmt:message bundle="${loc}" key="local.sentence.your_profile" var="your_profile"/>
<fmt:message bundle="${loc}" key="local.sentence.save_changes" var="save_changes"/>
<fmt:message bundle="${loc}" key="local.sentence.change_password_word" var="change_password_word"/>
<fmt:message bundle="${loc}" key="local.sentence.add_account_btn" var="add_account_btn"/>
<fmt:message bundle="${loc}" key="local.sentence.your_account" var="your_account"/>
<fmt:message bundle="${loc}" key="local.sentence.account_number" var="account_number"/>


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
        <%@include file="/front/css/profile.css" %>
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
<div>
    <div class="modal-dialog" style="padding-top: 65px;">
        <div class="modal-content" style="width: 750px;margin:0 0 0 -100px;">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="profile-form" class="form-horizontal form-horizontal-profile" method="post"
                              action="/epam.by/edit_profile">

                            <span class="heading">${your_profile}</span>

                            <div class="form-group">
                                <span class="profile-span">${login}</span>
                                <input type="text" class="form-control has-border" id="profile-login"
                                       value="${client.login}" disabled="disabled" name="login_profile">
                            </div>

                            <div class="form-group">
                                <span class="profile-span">${surname}</span>
                                <input type="text" class="form-control has-border" id="profile-surname"
                                       placeholder=${surname} name="surname" value="${client.surname}">
                                <span class="cd-error-message" id="surname-profile-span">${surname_mistake}</span>
                            </div>

                            <div class="form-group">
                                <span class="profile-span">${name}</span>
                                <input type="text" class="form-control has-border" id="profile-name"
                                       placeholder=${name} name="name" value="${client.name}">
                                <span class="cd-error-message" id="name-profile-span">${name_mistake}</span>

                            </div>

                            <div class="form-group">
                                <span class="profile-span">${email}</span>
                                <input type="email" class="form-control has-border" id="profile-email"
                                       placeholder=${email} value="${client.email}" name="email">
                                <span class="cd-error-message" id="email-profile-span">${email_mistake}</span>

                            </div>

                            <div id="flex-btn">
                                <c:if test="${account==null}">
                                    <button type="submit" class="btn btn-default">${add_account_btn}</button>
                                </c:if>
                                <button type="button" class="btn btn-default"
                                        id="changepassword">${change_password_word}</button>
                                <button type="submit" id="edit-button" class="btn btn-default">${save_changes}</button>
                            </div>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>


        <c:choose>
            <c:when test="${account!=null}">
            <div class="modal-content" style="width: 750px;margin:0 0 0 -100px;">
                <div class="container">
                    <div class="row">

                        <div class="col-md-6">
                            <form id="account-form" class="form-horizontal form-horizontal-profile" method="post"
                                  action="/epam.by/edit_profile">

                                <span class="heading">${your_account}</span>

                                <div class="form-group">
                                    <span class="profile-span">${account_number}</span>
                                    <input type="text" class="form-control has-border" id="account-login"
                                           value="${client.login}" disabled="disabled" name="login_profile">
                                </div>

                                    <%--<div class="form-group">--%>
                                    <%--<span class="profile-span">${surname}</span>--%>
                                    <%--<input type="text" class="form-control has-border" id="profile-surname"--%>
                                    <%--placeholder=${surname} name="surname" value="${client.surname}">--%>
                                    <%--<span class="cd-error-message" id="surname-profile-span">${surname_mistake}</span>--%>
                                    <%--</div>--%>

                                    <%--<div class="form-group">--%>
                                    <%--<span class="profile-span">${name}</span>--%>
                                    <%--<input type="text" class="form-control has-border" id="profile-name"--%>
                                    <%--placeholder=${name} name="name" value="${client.name}">--%>
                                    <%--<span class="cd-error-message" id="name-profile-span">${name_mistake}</span>--%>

                                    <%--</div>--%>

                                    <%--<div class="form-group">--%>
                                    <%--<span class="profile-span">${email}</span>--%>
                                    <%--<input type="email" class="form-control has-border" id="profile-email"--%>
                                    <%--placeholder=${email} value="${client.email}" name="email">--%>
                                    <%--<span class="cd-error-message" id="email-profile-span">${email_mistake}</span>--%>

                                    <%--</div>--%>

                                    <%--<div id="flex-btn">--%>
                                    <%--<c:if test="${account}==null">--%>
                                    <%--<button type="submit" class="btn btn-default">${add_account_btn}</button>--%>
                                    <%--</c:if>--%>
                                <button type="button" class="btn btn-default"
                                        id="changepassword">${change_password_word}</button>
                                    <%--<button type="submit" id="edit-button" class="btn btn-default">${save_changes}</button>--%>
                                    <%--</div>--%>
                            </form>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.container -->
            </div>
            </c:when>
        </c:choose>
    </div>
</div>


</body>
</html>
