---
plan-type: spec-implementation
tier: backend
---
## spec-implementation - launches - backend

### Step 1: Model and Enum Definition
Define the data structures for Launches.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/model/LaunchStatus.java
    - back/src/main/java/academy/aicode/astrobookings/model/Launch.java
- [ ] Create `LaunchStatus` enum with: `Created`, `Confirmed`, `Completed`, `Cancelled`.
- [ ] Create `Launch` model with `id` (UUID), `rocketId` (UUID), `launchDate` (LocalDateTime), `price` (BigDecimal), `minOccupancy` (Integer), and `status` (LaunchStatus).
- [ ] Add validation annotations (`@NotNull`, `@Min`, `@Max`) to `Launch` fields.

### Step 2: Repository Layer
Implement in-memory storage for Launches.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/repository/LaunchRepository.java
- [ ] Create `LaunchRepository` using a `ConcurrentHashMap<UUID, Launch>`.
- [ ] Implement `findAll()`, `findById()`, `save()`, and `deleteById()` methods.

### Step 3: Service Layer
Implement business logic and validation.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/service/LaunchService.java
- [ ] Create `LaunchService` injecting `LaunchRepository` and `RocketService`.
- [ ] Implement `createLaunch(Launch launch)`: validate input, verify rocket existence via `RocketService`, set initial status to `Created`.
- [ ] Implement `getAllLaunches()` and `getLaunchById(UUID id)`.
- [ ] Implement `updateLaunchStatus(UUID id, LaunchStatus status)`: find launch, update status, and save.

### Step 4: Controller Layer
Expose REST API endpoints.
- Paths:
    - back/src/main/java/academy/aicode/astrobookings/controller/LaunchController.java
- [ ] Create `LaunchController` with `@RestController` and `@RequestMapping("/api/launches")`.
- [ ] Implement `POST /` to create a launch.
- [ ] Implement `GET /` to list all launches.
- [ ] Implement `GET /{id}` to get launch details.
- [ ] Implement `PATCH /{id}/status` to update status.