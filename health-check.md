# Health check feature

## Role
Act as a senior software developer.

## Task
Implement a health check endpoint for the backend API and informs about its status in the frontend application.

## Context
The backend is a Java Spring application, and the frontend is a VUE application.

### Back
- Java Spring
- /back
- project file: /back/pom.xml
- start: `mvn spring-boot:run`

### Front
- VUE
- /front/astro-bookings
- project file: /front/astro-bookings/package.json
- start: `npm run serve`

The health check endpoint should return a JSON response with the status and uptime of the server.

The frontend should display this status in a user-friendly way.

## Steps
1. Implement the health check endpoint in the backend.
2. Create a service in the frontend to consume the health check endpoint.
3. Display the health status in the frontend application.

## Output
— Clean and functional code for the health check feature.
— No unit nor integration tests are required.

## Verification
— [ ] The server for the API starts without errors.
— [ ] The server for the frontend starts without errors.
— [ ] The health check endpoint returns the correct JSON response.
— [ ] The frontend displays the health status.