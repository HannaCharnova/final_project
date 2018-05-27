<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="epam.chernova.finalproject.entity.Review" scope="page" id="review"/>
<jsp:useBean class="epam.chernova.finalproject.entity.Client" scope="page" id="client_rev"/>

<fmt:setLocale scope="session" value="${locale}"/>
<fmt:setBundle basename="localization.pageInformation" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="local.word.cost" var="cost"/>
<fmt:message bundle="${loc}" key="local.word.weight" var="weight"/>

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


    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/form.js" %>
    </script>
    <style>
        <%@include file="/front/css/menu.css" %>
        <%@include file="/front/css/form.css" %>
        <%@include file="/front/css/common.css" %>
        <%@include file="/front/css/product.css" %>
        <%@include file="/front/css/orderlist.css" %>
        <%@include file="/front/css/review.css" %>

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
<div class="order-container">

    <div class="qa-message-list">

        <c:choose>
            <c:when test="${reviews!=null}">
                <c:forEach var="review" items="${reviews}">


                    <div class="message-item">
                        <div class="message-inner">
                            <div class="message-head clearfix">
                                <div class="user-detail">

                                    <c:forEach var="client_rev" items="${clients}">
                                        <c:if test="${review.idClient==client_rev.idUser}">
                                            <h5 class="handle">${client_rev.login} </h5>
                                            <div class="stars">
                                                <c:choose>
                                                    <c:when test="${review.mark>4}">
                                                        <input class="star-rating__input" id="star-rating-5-${review.idReview}"
                                                               type="radio"
                                                               name="rating5-${review.idReview}"
                                                               value="5" checked disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-5-${review.idReview}"
                                                               title="5 out of 5 stars"></label>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="star-rating__input" id="star-rating-5-${review.idReview}"
                                                               type="radio"
                                                               name="rating5-${review.idReview}"
                                                               value="5" disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-5-${review.idReview}"
                                                               title="5 out of 5 stars"></label>

                                                    </c:otherwise>
                                                </c:choose>


                                                <c:choose>
                                                    <c:when test="${review.mark>3}">
                                                        <input class="star-rating__input" id="star-rating-4-${review.idReview}"
                                                               type="radio"
                                                               name="rating4-${review.idReview}"
                                                               value="4" checked disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-4-${review.idReview}"
                                                               title="4 out of 5 stars"></label>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="star-rating__input" id="star-rating-4-${review.idReview}"
                                                               type="radio"
                                                               name="rating4-${review.idReview}"
                                                               value="4" disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-4-${review.idReview}"
                                                               title="4 out of 5 stars"></label>

                                                    </c:otherwise>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${review.mark>2}">
                                                        <input class="star-rating__input" id="star-rating-3-${review.idReview}"
                                                               type="radio"
                                                               name="rating3-${review.idReview}"
                                                               value="3" checked disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-3-${review.idReview}"
                                                               title="3 out of 5 stars"></label>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="star-rating__input" id="star-rating-3-${review.idReview}"
                                                               type="radio"
                                                               name="rating3-${review.idReview}"
                                                               value="3" disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-3-${review.idReview}"
                                                               title="3 out of 5 stars" ></label>

                                                    </c:otherwise>
                                                </c:choose>

                                                <c:choose>
                                                    <c:when test="${review.mark>1}">
                                                        <input class="star-rating__input" id="star-rating-2-${review.idReview}"
                                                               type="radio"
                                                               name="rating2-${review.idReview}"
                                                               value="2" checked disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-2-${review.idReview}"
                                                               title="2 out of 5 stars"></label>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="star-rating__input" id="star-rating-2-${review.idReview}"
                                                               type="radio"
                                                               name="rating2-${review.idReview}"
                                                               value="2" disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-2-${review.idReview}"
                                                               title="2 out of 5 stars"></label>

                                                    </c:otherwise>
                                                </c:choose>


                                                <c:choose>
                                                    <c:when test="${review.mark>0}">
                                                        <input class="star-rating__input" id="star-rating-1-${review.idReview}"
                                                               type="radio"
                                                               name="rating1-${review.idReview}" value="1" checked disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-1-${review.idReview}"
                                                               title="1 out of 5 stars"></label>

                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="star-rating__input" id="star-rating-1-${review.idReview}"
                                                               type="radio"
                                                               name="rating1-${review.idReview}" value="1" disabled>
                                                        <label class="star-rating__ico fa fa-star-o fa-lg"
                                                               for="star-rating-1-${review.idReview}"
                                                               title="1 out of 5 stars"></label

                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </c:if>
                                    </c:forEach>

                                </div>
                            </div>

                            <div class="qa-message-content">
                                <c:if test="${review.reviewText!=null}">
                                    <p>
                                            ${review.reviewText}
                                    </p>
                                </c:if>

                            </div>
                        </div>
                    </div>


                </c:forEach>
            </c:when>
        </c:choose>
    </div><!-- ./qa-message-list -->

</div>
<!-- ./container -->

</body>
</html>