<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Product" scope="page" id="product"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.weight" var="weight"/>

<html>
<head>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
    </script>
    <style>
        <%@include file="/front/css/product.css" %>
    </style>
    <title>Cafe</title>
</head>
<header>
    <%@ include file="/front/jsp/common/header.jsp" %>
</header>

<body>
<%@ include file="/front/jsp/common/information.jsp" %>
<%@ include file="/front/jsp/common/menu.jsp" %>
<%@ include file="/front/jsp/common/form.jsp" %>
<div class="container">
    <div class="row">

        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">

                    <div class="col-md-4 col-sm-6">
                        <div class="box">
                            <div class="pic">
                                <img src="/front/image/${product.imagePath}"
                                     alt=""/>
                            </div>
                                <%--<ul class="social-links">--%>
                                <%--<li><a href="#" class="fa fa-facebook"></a></li>--%>
                                <%--<li><a href="#" class="fa fa-google"></a></li>--%>
                                <%--<li><a href="#" class="fa fa-twitter"></a></li>--%>
                                <%--</ul>--%>
                            <div class="over-layer">
                                <h4 class="post">
                                    <a href="#">${product.name}</a>
                                    <small>${cost}: ${product.cost}</small>
                                    <small>${weight}: ${product.weight}</small>

                                </h4>
                                <span class="likes"><a href="#"><i class="fa fa-thumbs-up"></i>3</a></span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>

        <%--<div class="col-md-4 col-sm-6">--%>
            <%--<div class="box">--%>
                <%--<div class="pic">--%>
                    <%--<img src="http://drawings-girls.ucoz.net/2015/07/lico-vostochnoi-devushki.jpg" alt=""/>--%>
                <%--</div>--%>
                <%--<ul class="social-links">--%>
                    <%--<li><a href="#" class="fa fa-facebook"></a></li>--%>
                    <%--<li><a href="#" class="fa fa-google"></a></li>--%>
                    <%--<li><a href="#" class="fa fa-twitter"></a></li>--%>
                <%--</ul>--%>
                <%--<div class="over-layer">--%>
                    <%--<h4 class="post">--%>
                        <%--<a href="#">Kristiana</a>--%>
                        <%--<small>Lico s ulibkoi</small>--%>
                    <%--</h4>--%>
                    <%--<span class="likes"><a href="#"><i class="fa fa-thumbs-up"></i>7</a></span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div class="col-md-4 col-sm-6">--%>
            <%--<div class="box">--%>
                <%--<div class="pic">--%>
                    <%--<img src="http://drawings-girls.ucoz.net/2015/05/dve-devushki-i-letuchie-mishi.jpg" alt=""/>--%>
                <%--</div>--%>
                <%--<ul class="social-links">--%>
                    <%--<li><a href="#" class="fa fa-facebook"></a></li>--%>
                    <%--<li><a href="#" class="fa fa-google"></a></li>--%>
                    <%--<li><a href="#" class="fa fa-twitter"></a></li>--%>
                <%--</ul>--%>
                <%--<div class="over-layer">--%>
                    <%--<h4 class="post">--%>
                        <%--<a href="#">Nastya</a>--%>
                        <%--<small>Mechtatel Doo</small>--%>
                    <%--</h4>--%>
                    <%--<span class="likes"><a href="#"><i class="fa fa-thumbs-up"></i>9</a></span>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

    </div><!-- /.row -->
</div><!-- /.container -->

</body>
</html>