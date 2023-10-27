<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
</head>
<body>
<h2>장바구니</h2>
<table>
    <tr>
        <td colspan="4">장바구니 상품 목록</td>
    </tr>
    <tr>
        <th>상품명</th>
        <th>가격</th>
        <th>수량</th>
        <th>합계</th>
    </tr>
    <c:set var="tot" value="${0}" />
    <c:forEach items="${cart.productSetList}" var="productSet" varStatus="stat">
        <tr>
            <td>${productSet.product.Pro_Name}</td>
            <td>${productSet.product.Pro_Price}</td>
            <td>${productSet.quantity}</td>
            <td>${productSet.quantity * productSet.product.Pro_Price}</td>
            <td>
                <a href="cartDelete?index=${stat.index}">Remove</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="4" align="right">총 구입 금액:
            <c:out value="${tot}" />
            원
        </td>
    </tr>
</table>
<br>
${message}
<br>
<a href="../productbuy/list">상품목록</a>
<a href="checkout">주문하기</a>
</body>
</html>
