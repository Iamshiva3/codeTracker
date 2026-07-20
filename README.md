# CodeTrack

CodeTrack is a simple full-stack coding tracker that helps students log their programming practice, view activity history, and receive AI-style suggestions.

## Features

- User authentication and signup/login
- Log coding activity by date, platform, time spent, and problems solved
- View recent activity history
- See AI-style suggestions based on tracked activity
- In-memory H2 database for local development

## Project Structure

- backend/ - Spring Boot REST API and business logic
- frontend/ - Static web UI for dashboard and authentication pages

## Tech Stack

- Java 25
- Spring Boot 3.2.5
- Spring Security
- Spring Data JPA
- H2 Database
- HTML/CSS/JavaScript

## Prerequisites

- JDK 25
- Maven
- A modern browser

## Running the Backend

From the project root:

```bash
cd backend
mvn spring-boot:run
```

The backend runs on:

- http://localhost:8081

## Running the Frontend

Open the frontend files directly in a browser, or serve the folder with a simple static server:

```bash
cd frontend
python -m http.server 8000
```

Then open:

- http://localhost:8000/index.html

## Default Backend Configuration

The backend uses an in-memory H2 database and is configured in:

- backend/src/main/resources/application.properties

## Notes

- The application is intended for local development and demo use.
- Authentication and JWT settings are currently configured in the backend properties file.

## License

This project is for educational/demo purposes.


Prerequisites


Java (JDK) installed for running the backend
A modern web browser to view the frontend
