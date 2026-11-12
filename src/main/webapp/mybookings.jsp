
<%@ page import="java.util.List" %>
<%@ page import="com.busbooking.entity.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
</head>
<body>

<h1>My Bus Bookings</h1>

<%
    List<Booking> bookings =
        (List<Booking>) request.getAttribute("bookings");

    if (bookings != null && !bookings.isEmpty()) {

        for (Booking booking : bookings) {
%>

    <hr>

    <h2>Booking ID: <%= booking.getId() %></h2>

    <p>Bus Name: <%= booking.getBus().getBusName() %></p>

    <p>Source: <%= booking.getBus().getSource() %></p>

    <p>Destination: <%= booking.getBus().getDestination() %></p>

    <p>Seats Booked: <%= booking.getSeatsBooked() %></p>

    <p>Total Amount: Rs. <%= booking.getTotalAmount() %></p>

    <p>Booking Date: <%= booking.getBookingDate() %></p>

    <p>Status: <%= booking.getStatus() %></p>

    <% if ("CONFIRMED".equals(booking.getStatus())) { %>

        <form action="cancelBooking" method="post">

            <input type="hidden"
                   name="bookingId"
                   value="<%= booking.getId() %>">

            <button type="submit">Cancel Booking</button>

        </form>

    <% } %>

<%
        }

    } else {
%>

    <h3>You have no bookings yet!</h3>

<%
    }
%>

<br>
<a href="index.jsp">Back to Home</a>

</body>
</html>
