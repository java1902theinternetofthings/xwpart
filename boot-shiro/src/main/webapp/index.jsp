<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
    <h3>登录</h3>
    <h3 th:text="${msg}" style="color: red;"></h3>
    <form method="post" action="login">
        用户名：<input type="text" name="name"><br/>
        密码:<input type="password" name="password"><br/>
        <input type="submit">
    </form>
</body>
</html>