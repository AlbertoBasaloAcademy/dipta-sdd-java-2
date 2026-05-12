---
plan-type: spec-implementation
tier: backend
---
## spec-implementation - observability-feedback - backend

### Step 1: Add business event logging to services
Add Console Logger to LaunchService and RocketService and log key business events.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/service/LaunchService.java
    - back/src/main/java/academy/aicode/astrobookings/service/RocketService.java
- [ ] Add Logger to LaunchService and log launch creation and status updates.
- [ ] Add Logger to RocketService and log rocket creation and decommissioning.

### Step 2: Enhance global error logging
Update GlobalExceptionHandler to log all exceptions with stack traces.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/controller/GlobalExceptionHandler.java
- [ ] Add Logger to GlobalExceptionHandler.
- [ ] Log handled exceptions at ERROR level.
