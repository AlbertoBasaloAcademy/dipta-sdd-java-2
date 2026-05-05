# Session 1. 

## 1. Ecosystem

### 1.1. AstroBookings Project

```bash
mkdir astrobookings
cd astrobookings
git init

# gitignore
echo "node_modules/" > .gitignore

# README.md
echo -e "# AstroBookings \n A REST API and a web application for the fictional space tourism company (AstroBookings).\n Its purpose is to support its employees in their daily operations. Manage the rocket fleet, plan and confirm or cancel launches, as well as record passenger bookings without overbooking. Payments and refunds are processed through a fictional payment gateway.\n It is an MVP, so it does not require authentication or storage features." > README.md

mkdir back
mkdir front
```

### 1.2. Backend
```bash
## Backend
cd back

# Java Spring
curl https://start.spring.io/starter.zip \
  -d dependencies=web \
  -d type=maven-project \
  -d language=java \
  -d javaVersion=25 \
  -d groupId=academy.aicode \
  -d artifactId=astrobookings \
  -d name=astrobookings \
  -d packageName=academy.aicode.astrobookings \
  -o back.zip
unzip back.zip

# Node Express
curl -L https://github.com/AlbertoBasalo/express-boilerplate/archive/refs/heads/main.tar.gz \
  | tar -xz
  --strip-components=1
  -C ./back
```

### 1.3. Frontend
```bash
# Frontend
cd front

# Angular
ng new AstroBookings
  --ai-config=copilot
  --routing
  --skip-git
  --ssr=false
  --style=css
  --zoneless
  --dry-run


# Vue
npm install -g @vue/cli
vue create astro-bookings


```

---

## 2. Vibes

### 2.1 Health check Vibes

```md
Create a health check endpoint for the backend API and informs about its status in the frontend application.
```

### 2.2. Prompt Engineering Vibes

```markdown
# Health check feature

## Role
Act as a senior software developer.

## Task
Implement a health check endpoint for the backend API and informs about its status in the frontend application.

## Context
The backend is a {lang/framework} application, and the frontend is a {lang / framework} application.

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
```

---

## 3. AI-Driven Development

### 3.1. Product Specification
`fr01-rockets-fleet-mng.spec.md`

```markdown
# Rockets Fleet Management 

## Problem definition
— As an operator, I want to **register a rocket with its capacity and range of action** to enable its use in launch planning.
— As an operator, I want to **consult the rocket catalog** to quickly select a vehicle compatible with the destination.
— As an operator, I want to **update the operational data of a rocket** to maintain reliable information for business decisions.
— As an operator, I want to **decommission a rocket** to prevent its use in future launches.

## Solution overview
### Data Model
— Rocket:
    — id: uuid#
    — name: string#
    — capacity: number, [1..9]
    — range: enum, (Earth, Moon, Mars)
    — decommissioned: boolean?

### Backend API
— An endpoint to create a rocket with its capacity and range of action.
— An endpoint to consult the rocket catalog.
— An endpoint to update the operational data of a rocket.
— An endpoint to decommission a rocket.

### Frontend Application
— A form to register a new rocket with its capacity and range of action.
— A view to consult the rocket catalog.
— A form to update the operational data of a rocket.
— A button to decommission a rocket.
  
## Acceptance and Release
— [ ] IF a new rocket is registered with valid data, THEN it should be added to the catalog.
— [ ] IF the rocket catalog is consulted, THEN it should display all registered rockets.
— [ ] IF a rocket's operational data is updated, THEN the changes should be reflected in the catalog.
— [ ] IF a rocket is decommissioned, THEN it should no longer be available for future launches.
— [ ] WHEN invalid data is provided, THEN the system should return appropriate error messages.
— [ ] WHEN a rocket is found by its ID, THEN the system should return a not found error.
```

### 3.2 Technology rules for agents

`AGENTS.md`
```markdown
# Rules and instructions for agents.

## Product
AstroBookings purpose is to support the employees of a fictional space travel agency in their daily operations. It is an MVP, so it does not require authentication or storage features.

## Technology
Is a simple system with an REST API at the backend and a web application as a frontNo database is needed, just memory for the moment.

### Back REST API
— Tech Stack: Java, Spring, Maven, Junit.
— Scaffolding: `/back`, `/back/pom.xml`
— Scripts: `mvn spring-boot:run` `mvn test`

### Front Web application 
— Tech Stack: TypeScript, HTML, CSS, Angular, Vitest, esbuild.
— Scaffolding: `/front`, `front/package.json`, `front/angular.json`
— Scripts: `npm start`, `npm test`
  
## Environment

### Configuration
— This file with the main guidelines at `/AGENTS.md`
— Skills, commands, prompts... at `/.agents/`
  
### Conventions
— Use conventional commit messaging
— Code branches are: `feat/` `fix/` `chore/`
— Generate short slugs for artifacts and names (less-than-20-chars).

### Behavior
— Be extremely concise. Sacrifice grammar for concision.
— Do not explain if not asked for.
— When you are in doubt, ask questions one by one with closed answers. 
```

### 3.3 Prompt Engineering + HITL
`/codify`
  
```markdown
# Write code to implement a feature

## Role
Act as a software engineer

## Task
Implement the feature, which includes writing unit test for critical modules.

## Context
— A request made by the user with a concrete requirement.
— A specification file with a feature request or bug to fix.

## Steps
1. **Think before coding**. Reason about the problem and ask to clarify.
2. **Simplicity first**. Avoid complex, clever or Y.A.G.N.I. solutions.
3. **Surgical changes**. Write the minimum amount of code.
4. **Goal-driven execution**. Keep working until verified.
  
## Output
— A fully functional code with a green unit test that fulfills the criteria.

## Verification
— [ ] Code compiles without errors.
— [ ] Unit test passes.
— [ ] Smoke test (app or servers starts).
— [ ] Acceptance criteria are met.
```
