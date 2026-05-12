---
spec-slug: observability-feedback
---
# Specification for Observability and User Feedback

## Problem definition

The application currently lacks proper logging for business-critical events and errors in both the backend and frontend. This makes monitoring, debugging, and auditing difficult. Furthermore, users do not receive clear feedback in the UI when actions (like scheduling a launch) succeed or fail, which negatively impacts usability.

### User Stories

- As an operator, I want to see console logs for business events so that I can monitor the application's activity.
- As a developer, I want to see detailed error logs in the console so that I can debug issues quickly.
- As a user, I want to receive clear notifications for the success or failure of my actions so that I know the state of my requests.

## Solution overview

### Data Model

No new persistent entities are required for this feature.

### Backend Tier

- **Logging**:
    - Use basic console `Logger` in `LaunchService` and `RocketService` to log business events at `INFO` level.
    - Logged events include: Launch creation, Launch status update, Rocket creation, Rocket decommissioning.
- **Error Handling**:
    - Update `GlobalExceptionHandler` to log all exceptions at `ERROR` level, including the stack trace for unhandled exceptions.

### Frontend Tier

- **Console Logs**:
    - Implement `console.info` for successful business actions and `console.error` for failed API requests in the service layer.
- **User Feedback**:
    - Implement a `NotificationService` (or similar simple reactive state) to manage global notifications.
    - Add a `NotificationToast` component to `App.vue` to display these messages.
    - Update components (`LaunchForm.vue`, `RocketFleet.vue`, etc.) to trigger notifications upon completion of asynchronous actions.

## Acceptance and Release

- [ ] WHEN a business event occurs in the backend THEN THE [Backend] SHALL log an INFO message to the console.
- [ ] IF an exception occurs in the backend THEN THE [Backend] SHALL log an ERROR message with the stack trace to the console.
- [ ] WHEN an API call is made from the frontend THEN THE [Frontend] SHALL log the event and any errors to the console.
- [ ] WHEN a user action succeeds THEN THE [Frontend] SHALL display a success notification in the UI.
- [ ] WHEN a user action fails THEN THE [Frontend] SHALL display an error notification in the UI.
