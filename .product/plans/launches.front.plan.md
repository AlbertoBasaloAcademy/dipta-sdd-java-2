---
plan-type: spec-implementation
tier: frontend
---
## spec-implementation - launches - frontend

### Step 1: API Service
Implement communication with the backend.
- Paths:
    - front/src/services/launchService.js
- [ ] Create `launchService.js` using `fetch` to call backend endpoints.
- [ ] Implement `getAllLaunches()`, `getLaunchById(id)`, `createLaunch(launch)`, and `updateLaunchStatus(id, status)`.

### Step 2: Launch Form Component
Create a form to schedule new launches.
- Paths:
    - front/src/components/LaunchForm.vue
- [ ] Create `LaunchForm.vue` with fields for Rocket (select), Date, Price, and Min Occupancy.
- [ ] Fetch rockets from `rocketService.js` to populate the selection dropdown.
- [ ] Implement form submission calling `launchService.createLaunch()`.

### Step 3: Launch Dashboard Component
Create a dashboard to view and manage launches.
- Paths:
    - front/src/components/LaunchDashboard.vue
- [ ] Create `LaunchDashboard.vue` to list all launches in a table.
- [ ] Display launch details: Rocket ID, Date, Price, Min Occupancy, and Status.
- [ ] Add buttons/controls to update launch status (Confirm, Complete, Cancel).

### Step 4: Application Integration
Integrate new components into the main view.
- Paths:
    - front/src/App.vue
- [ ] Import and add `LaunchForm` and `LaunchDashboard` to the template.