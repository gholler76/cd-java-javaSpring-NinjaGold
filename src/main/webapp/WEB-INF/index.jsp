<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<style><%@include file="static/css/style.css"%></style>
<title>Ninja Gold</title>
</head>
<body>
<!-- game header -->
<div class="container-fluid">
    <div class="row justify-content-start bg-black">
        <div class="col-3">
            <h1>YOUR GOLD: </h1>
            <div class="gold-text"><c:out value="${gold}"/></div>
        </div>
    </div>
</div>

<!-- game form deck -->
<div class="container-fluid">
    <div class="row justify-content-around bg-gold">
        <div class="col-2">
            <form action="/process" method="POST">
                <input type="hidden" name="activity" value="farm">
                <h1>Farm</h1>
                <button type="submit" id="button-farm"></button>
                <h2>Find Gold! &#8679;</h2>
            </form>
        </div>
        <div class="col-2">
            <form action="/process" method="POST">
                <input type="hidden" name="activity" value="cave">
                <h1>Cave</h1>
                <button type="submit" id="button-cave"></button>
                <h2>Find Gold! &#8679;</h2>
            </form>
        </div>
        <div class="col-2">
            <form action="/process" method="POST">
                <input type="hidden" name="activity" value="house">
                <h1>House</h1>
                <button type="submit" id="button-house"></button>
                <h2>Find Gold! &#8679;</h2>
            </form>
        </div>
        <div class="col-2">
            <form action="/process" method="POST">
                <input type="hidden" name="activity" value="casino">
                <h1>Casino</h1>
                <button type="submit" id="button-farm"></button>
                <h2>Win Gold! &#8679;</h2>
            </form>
        </div>
    </div>
</div>


<!-- game activity viewer -->
<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="log">
          <c:out value="${log}"/>
        </div>
    </div>
</div>
</body>
</html>