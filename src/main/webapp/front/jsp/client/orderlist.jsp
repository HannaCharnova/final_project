<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Order" scope="page" id="order"/>
<jsp:useBean class="epam.chernova.finalproject.entity.Product" scope="page" id="product"/>
<jsp:useBean class="epam.chernova.finalproject.entity.OrderProduct" scope="page" id="orderProduct"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.order_word" var="order_word"/>
<fmt:message bundle="${loc}" key="local.word.total_cost" var="total_cost"/>
<fmt:message bundle="${loc}" key="local.word.remove_from_basket" var="remove_from_basket"/>
<fmt:message bundle="${loc}" key="local.word.has_been_paid" var="has_been_paid"/>
<fmt:message bundle="${loc}" key="local.word.pay" var="pay"/>
<fmt:message bundle="${loc}" key="local.word.not_exist" var="not_exist"/>

<style>
    <%@include file="/front/css/orderlist.css" %>
</style>

<div class="order-container">

    <div class="qa-message-list">

        <c:choose>
            <c:when test="${orders!=null}">
                <c:forEach var="order" items="${orders}">


                    <div class="message-item">
                        <div class="message-inner">
                            <div class="message-head clearfix">
                                <form action="/epam.by/pay_for_order?idOrder=${order.idOrder}" method="post">
                                    <div class="user-detail">
                                        <h5 class="handle">${order_word} ${order.idOrder} </h5>
                                        <c:choose>
                                            <c:when test="${order.status eq 'true'}">
                                                <button type="submit" class="btn-right"
                                                        class="btn btn-default">${pay}</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" disabled="disabled" class="btn-right"
                                                        class="btn btn-default">${has_been_paid}</button>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="post-meta">
                                            <div class="asker-meta">
                                                <span class="qa-message-what"></span>
                                                <span class="qa-message-when">
 <span class="qa-message-when-data">${order.date}</span>
 </span>
                                                <span class="qa-message-who">
 <span class="qa-message-who-pad">${total_cost} </span>
 <span class="qa-message-who-data">${order.totalCost}</span>
 </span>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="qa-message-content">
                                <c:forEach var="orderProduct" items="${order_products}">

                                    <c:if test="${orderProduct.idOrder==order.idOrder}">
                                        <c:forEach var="product" items="${products}">
                                            <c:if test="${orderProduct.idProduct==product.idProduct}">
                                                <form action="/epam.by/remove_product_basket?idProduct=${product.idProduct}&idOrder=${order.idOrder}&quantity=${orderProduct.quantity}"
                                                      method="post">

                                                    <p>
                                                        <c:choose>
                                                            <c:when test="${locale eq 'ru'}">
                                                                ${product.nameRu} (${product.cost} BYN x ${orderProduct.quantity})
                                                            </c:when>
                                                            <c:when test="${locale eq 'en'}">
                                                                ${product.nameEn} (${product.cost} BYN x ${orderProduct.quantity})
                                                            </c:when>
                                                        </c:choose>

                                                        <c:choose>
                                                            <c:when test="${product.exist eq 'false'}">
                                                                <small style="float: right">${not_exist}</small>
                                                            </c:when>
                                                        </c:choose>

                                                        <c:choose>
                                                            <c:when test="${order.status eq 'true'}">
                                                                <button type="submit" class="btn-right"
                                                                        class="btn btn-default">${remove_from_basket}</button>
                                                            </c:when>
                                                        </c:choose>
                                                    </p>
                                                </form>
                                            </c:if>
                                        </c:forEach>

                                    </c:if>


                                </c:forEach>
                            </div>
                        </div>
                    </div>


                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->

</div>
<!-- ./container -->
