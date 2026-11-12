
<%@ page import="java.util.List" %>
<%@ page import="com.busbooking.entity.bus" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Available Buses</title>
</head>
<body>

<h1>Available Buses</h1>

<%
    List<bus> buses =
        (List<bus>) request.getAttribute("buses");

    if (buses != null && !buses.isEmpty()) {

        for (bus bus : buses) {
%>

    <hr>

    <h2><%= bus.getBusName() %></h2>

    <p>Bus Number: <%= bus.getBusNumber() %></p>
    <p>Source: <%= bus.getSource() %></p>
    <p>Destination: <%= bus.getDestination() %></p>
    <p>Departure: <%= bus.getDepartureTime() %></p>
    <p>Arrival: <%= bus.getArrivalTime() %></p>
    <p>Price: Rs. <%= bus.getPrice() %></p>
    <p>Available Seats: <%= bus.getAvailableSeats() %></p>

    <% if (bus.getAvailableSeats() > 0) { %>

        <form action="booking.jsp" method="get">

            <input type="hidden"
                   name="busId"
                   value="<%= bus.getId() %>">

            <button type="submit">Book Now</button>

        </form>

    <% } else { %>

        <p>No seats available.</p>

    <% } %>

<%
        }

    } else {
%>

    <h3>No buses found!</h3>

<%
    }
%>

<br>
<a href="index.jsp">Back to Home</a>

</body>
</html>
