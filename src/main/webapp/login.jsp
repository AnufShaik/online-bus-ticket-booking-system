
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>

<h1>User Login</h1>

<form action="login" method="post">

    <label>Email:</label>
    <input type="email" name="email" required>

    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>

    <br><br>

    <button type="submit">Login</button>

</form>

<p>New user? <a href="register.jsp">Register here</a></p>

</body>
</html>
