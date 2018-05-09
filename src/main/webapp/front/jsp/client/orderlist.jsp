<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Order" scope="page" id="order"/>
<jsp:useBean class="epam.chernova.finalproject.entity.Product" scope="page" id="product"/>
<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.order_word" var="order_word"/>
<fmt:message bundle="${loc}" key="local.word.total_cost" var="total_cost"/>

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

                                <div class="user-detail">
                                    <h5 class="handle">${order_word} ${order.idOrder} </h5>
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
                            </div>

                            <div class="qa-message-content">
                                Mohtashim is an MCA from AMU (Aligarah) and a Project Management Professional. He has
                                more than 17
                                years of experience in Telecom and Datacom industries covering complete SDLC. He is
                                managing
                                in-house innovations, business planning, implementation, finance and the overall
                                business
                                development of Tutorials Point.
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->

</div>
<!-- ./container -->
