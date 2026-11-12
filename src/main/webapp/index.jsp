
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Bus Booking</title>
</head>
<body>

<h1>Online Bus Ticket Booking System</h1>

<nav>
    <a href="index.jsp">Home</a> |
    <a href="register.jsp">Register</a> |
    <a href="login.jsp">Login</a> |
    <a href="myBookings">My Bookings</a>
</nav>

<hr>

<h2>Search Buses</h2>

<form action="searchBus" method="post">

    <label>From:</label>
    <input type="text" name="source" required>

    <br><br>

    <label>To:</label>
    <input type="text" name="destination" required>

    <br><br>

    <button type="submit">Search Buses</button>

</form>

</body>
</html>
