<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Product" scope="page" id="product"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.weight" var="weight"/>
<fmt:message bundle="${loc}" key="local.word.delete_product" var="delete_product"/>
<fmt:message bundle="${loc}" key="local.word.add_product" var="add_product"/>

<style>
    <%@include file="/front/css/productlist.css" %>
</style>
<div class="container">
    <button  class="btn btn-default" id="add-button"><a id="signup">${add_product}</a></button>
    <div class="row">

        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">
                    <form action="/epam.by/delete_product?idProduct=${product.idProduct}" method="post">
                        <div class="col-md-4 col-sm-6">
                            <div class="box">
                                <div class="pic">
                                    <img src="/front/image/menu/${product.imagePath}"
                                         alt=""/>
                                </div>
                                <div class="over-layer">
                                    <h4 class="post">
                                        <c:choose>
                                            <c:when test="${locale eq 'ru'}">
                                                <a href="#">${product.nameRu}</a>
                                            </c:when>
                                            <c:when test="${locale eq 'en'}">
                                                <a href="#">${product.nameEn}</a>
                                            </c:when>
                                        </c:choose>
                                        <small>${cost}: ${product.cost} BYN</small>
                                        <small>${weight}: ${product.weight} g</small>
                                        <small><button type="submit" class="btn btn-default">${delete_product}</button></small>
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </c:when>
        </c:choose>


    </div><!-- /.row -->
</div>
<!-- /.container -->
