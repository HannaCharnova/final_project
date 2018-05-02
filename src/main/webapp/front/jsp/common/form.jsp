<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.authorization" var="authorization"/>
<fmt:message bundle="${loc}" key="local.word.login" var="login"/>
<fmt:message bundle="${loc}" key="local.word.password" var="password"/>
<fmt:message bundle="${loc}" key="local.word.enter" var="enter"/>
<fmt:message bundle="${loc}" key="local.word.administrator" var="administrator"/>
<fmt:message bundle="${loc}" key="local.word.registration" var="registration"/>
<fmt:message bundle="${loc}" key="local.word.name" var="name"/>
<fmt:message bundle="${loc}" key="local.word.surname" var="surname"/>
<fmt:message bundle="${loc}" key="local.word.email" var="email"/>
<fmt:message bundle="${loc}" key="local.word.registrate" var="registrate"/>
<fmt:message bundle="${loc}" key="local.sentence.login_mistake" var="login_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.password_mistake" var="password_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.name_mistake" var="name_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.surname_mistake" var="surname_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.email_mistake" var="email_mistake"/>
<fmt:message bundle="${loc}" key="local.sentence.re_password" var="re_password"/>


<head>
    <meta name="viewport" content="width=device-width, initial-scale = 1">
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.css"/>
    <script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
    <script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
    <script src="http://bootstraptema.ru/snippets/menu/2016/slidemenu/slidemenu.js"></script>
    <style>
        <%@include file="/front/css/form.css" %>
    </style>
    <script>
        <%@include file="/front/js/form.js" %>
    </script>
</head>


<div class="modal" class="modal fade" class="cd-user-modal" id="sign_in" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="sign-in-form" class="form-horizontal form-horizontal-log" method="post" action="/epam.by/sign_in">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${authorization}</span>

                            <div class="form-group">
                                <input type="text" class="form-control has-border" id="inputLogin"
                                       placeholder=${login} name="login_in">
                                <span class="cd-error-message" id="login-span">${login_mistake}</span>

                            </div>
                            <div class="form-group help">
                                <input type="password" class="form-control has-border" id="inputPassword"
                                       placeholder=${password} name="password_in">
                                <span class="cd-error-message" id="password-span">${password_mistake}</span>

                            </div>
                            <div class="form-group">
                                <div class="main-checkbox">
                                    <input type="checkbox" id="checkbox1" name="check"/>
                                    <label for="checkbox1"></label>
                                </div>
                                <span class="text">${administrator}</span>
                                <button type="submit" id="signin-button"  class="btn btn-default">${enter}</button>
                            </div>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>


<div class="modal" class="modal fade" class="cd-user-modal" id="sign_up" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="row">

                    <div class="col-md-6">
                        <form id="sign-up-form" class="form-horizontal form-horizontal-reg" method="post" action="/epam.by/sign_up">

                            <button type="button" class="close" data-dismiss="modal" class="close">&times;</button>
                            <span class="heading">${registration}</span>

                            <div class="form-group">
                                <input type="text" class="form-control has-border" id="name"
                                       placeholder=${name} name="name">
                                <span class="cd-error-message" id="name-up-span">${name_mistake}</span>

                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control has-border" id="surname"
                                       placeholder=${surname} name="surname">
                                <span class="cd-error-message" id="surname-up-span">${surname_mistake}</span>

                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control has-border" id="email"
                                       placeholder=${email} name="email">
                                <span class="cd-error-message" id="email-up-span">${email_mistake}</span>

                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control has-border" id="login"
                                       placeholder=${login} name="login_up">
                                <span class="cd-error-message" id="login-up-span">${login_mistake}</span>

                            </div>
                            <div class="form-group help">
                                <input type="password" class="form-control has-border" id="password"
                                       placeholder=${password} name="password_up">
                                <span class="cd-error-message" id="password-up-span">${password_mistake}</span>

                            </div>

                            <button type="submit" id="signup-button"  class="btn btn-default">${registrate}</button>
                        </form>
                    </div>
                </div><!-- /.row -->
            </div><!-- /.container -->
        </div>
    </div>
</div>




