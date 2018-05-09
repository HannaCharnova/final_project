<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Product" scope="page" id="product"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.weight" var="weight"/>
<fmt:message bundle="${loc}" key="local.word.add_to_basket" var="add_to_basket"/>

<style>
    <%@include file="/front/css/productlist.css" %>
</style>

<div class="container">
    <div class="row">

        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">
                    <form action="/epam.by/add_product_basket?idProduct=${product.idProduct}" method="post">
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
                                        <small>${cost}: ${product.cost}</small>
                                        <small>${weight}: ${product.weight}</small>
                                        <small><input type="number" name="quantity" value="0" class="input-number"></small>
                                        <small><button type="submit" class="btn btn-default">${add_to_basket}</button></small>
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

</body>
</html>