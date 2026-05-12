---
spec-slug: launches
---
# Specification for Rocket Launches Feature

## Problem definition

AstroBookings needs to allow operators to schedule and manage rocket launches. Currently, there is no way to plan flights, set pricing, or track the status of a mission (e.g., whether it's confirmed or cancelled). This feature will enable the core business of selling tickets and executing space flights.

### User Stories

- As an operator, I want to **schedule a new launch** by selecting a rocket and specifying the time, price, and minimum occupancy so that I can open it for bookings.
- As an operator, I want to **view all scheduled launches** so that I can have an overview of the fleet's activity.
- As an operator, I want to **manage the status of a launch** (confirm, complete, or cancel) so that the system accurately reflects the operational reality.

## Solution overview

### Data Model

- **Launch**: A scheduled flight using a specific rocket.
    - id: uuid#
    - rocket: Rocket [1-n]
    - launch_date: date_time
    - price: decimal
    - min_occupancy: integer [1, 99]
    - status: enum [Created, Confirmed, Completed, Cancelled]
- Occupancy: A percentage of rocket capacity to be economically attractive.
- A rocket can be reused for multiple launches.

### Backend API

- `POST /launches`: Create a new launch with 'Created' status.
- `GET /launches`: Retrieve a list of all launches.
- `GET /launches/{id}`: Retrieve details of a specific launch.
- `PATCH /launches/{id}/status`: Update the status of a launch.
- Validate input data.
- Respond with minimal DTOs.

### Frontend Application

- **Launches Dashboard**: A view listing all scheduled launches with their details and current status.
- **Schedule Launch Form**: A form to input launch details and select an available rocket.
- **Status Controls**: Buttons or a dropdown in the launch details view to change its status.

### Database Schema

- Only memory at MVP stage.  
- `launches` table:
    - `id`: UUID (Primary Key)
    - `rocket_id`: UUID (Foreign Key to `rockets.id`)
    - `launch_date`: TIMESTAMP
    - `price`: DECIMAL
    - `min_occupancy`: INTEGER
    - `status`: VARCHAR

## Acceptance and Release

- [ ] WHEN an operator schedules a launch with valid data, THE [System] SHALL save it with 'Created' status.
- [ ] THE [System] SHALL display all scheduled launches in the dashboard.
- [ ] WHEN an operator updates a launch status, THE [System] SHALL persist the new status.
- [ ] IF a launch is updated to 'Cancelled', THEN THE [System] SHALL reflect this status in the list.
- [ ] WHEN scheduling a launch, THE [System] SHALL verify that the selected rocket exists.
