
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>

<h1>User Registration</h1>

<form action="register" method="post">

    <label>Name:</label>
    <input type="text" name="name" required>

    <br><br>

    <label>Email:</label>
    <input type="email" name="email" required>

    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>

    <br><br>

    <label>Phone:</label>
    <input type="text" name="phone" required>

    <br><br>

    <button type="submit">Register</button>

</form>

<p>Already registered? <a href="login.jsp">Login here</a></p>

</body>
</html>
