<%--
  Created by IntelliJ IDEA.
  User: HUYNH-PHUC Axel
  Date: 05/12/2018
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ShareLoc</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="text-center">
            <h1>ShareLoc</h1>
        </div>
        <h2>Login</h2>

        <form id="log" action='users/log' method="post">
            <input type="text" class="form-control" name="username" placeholder="Username">
            <input type="password" class="form-control" name="user_password" placeholder="Password">
            <input id="login" type="submit" class="btn btn-light" value="Login">
        </form>

        <a id="sign_in" href='sign.jsp' class="btn btn-info" role="button">Sign in</a>

    </div>
</body>

    <footer>
        <div class="text-center">
            <p>Developed by HUYNH-PHUC Axel</p>
        </div>
    </footer>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>