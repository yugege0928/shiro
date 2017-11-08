<%--
  Created by IntelliJ IDEA.
  User: yugege
  Date: 17/11/7
  Time: 下午2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
<center>
    <form action="/loginsubmit" method="post">

        账号:<input type="text" name="usercode"><br/>
        密码:<input type="password" name="password"><br/>

        <input type="submit" value="登录">
    </form>
</center>
</body>
</html>
