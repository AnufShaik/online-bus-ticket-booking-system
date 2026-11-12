
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Bus</title>
</head>
<body>

<h1>Book Your Bus Ticket</h1>

<form action="book" method="post">

    <input type="hidden"
           name="busId"
           value="<%= request.getParameter("busId") %>">

    <label>Number of Seats:</label>

    <input type="number"
           name="seatsBooked"
           min="1"
           required>

    <br><br>

    <button type="submit">Confirm Booking</button>

</form>

<br>
<a href="index.jsp">Back to Home</a>

</body>
</html>
