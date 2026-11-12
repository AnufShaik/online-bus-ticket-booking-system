# online-bus-ticket-booking-system
An Online Bus Ticket Booking System developed using Java, JSP, Servlets, Hibernate (JPA), PostgreSQL, and Apache Tomcat. Users can register, log in, search for available buses, book tickets, view bookings, and cancel reservations.


## Project Overview

The Online Bus Ticket Booking System is a web-based application developed using Java technologies. It allows users to register, log in, search for available buses, book tickets, view their bookings, and cancel reservations.

## Features

- User Registration
- User Login and Logout
- Search Buses by Source and Destination
- View Available Buses
- Book Bus Tickets
- View My Bookings
- Cancel Bookings
- Database Integration using PostgreSQL

## Technologies Used

- **Programming Language:** Java
- **Frontend:** HTML, CSS, JSP
- **Backend:** Java Servlets
- **ORM Framework:** Hibernate with JPA
- **Database:** PostgreSQL
- **Server:** Apache Tomcat 10
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA

## Project Structure

```text
online-bus-ticket-booking-system/
│
├── pom.xml
│
└── src/
    └── main/
        ├── java/
        │   └── com/busbooking/
        │       ├── entity/
        │       ├── servlet/
        │       └── util/
        │
        ├── resources/
        │   └── META-INF/
        │       └── persistence.xml
        │
        └── webapp/
            ├── WEB-INF/
            │   └── web.xml
            ├── index.jsp
            ├── register.jsp
            ├── login.jsp
            ├── buses.jsp
            ├── booking.jsp
            └── mybookings.jsp
```

## Prerequisites

Make sure you have installed:

1. JDK 25
2. IntelliJ IDEA
3. Apache Tomcat 10
4. PostgreSQL
5. Maven

## Database Configuration

1. Create a PostgreSQL database named `busbooking`.
2. Configure your database connection in `src/main/resources/META-INF/persistence.xml`.
3. Update the database username and password with your own PostgreSQL credentials.
4. Keep your actual `persistence.xml` private. Do not upload database passwords to GitHub.

## How to Run

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Configure the PostgreSQL database connection.
4. Reload the Maven project.
5. Configure Apache Tomcat 10.
6. Deploy and run the application.
7. Open the application in your browser.

## Application Modules

- **Registration:** Allows new users to create an account.
- **Login:** Allows registered users to log in.
- **Bus Search:** Searches for buses based on source and destination.
- **Booking:** Allows users to reserve available seats.
- **My Bookings:** Displays the user's booking details.
- **Cancellation:** Allows users to cancel confirmed bookings.

## Author

Developed as a Java Web Application Project.
