---
name : initialize
description : Initialize the environment by loading all necessary commands, rules, and skills. This should be the first skill activated to set up the context for subsequent tasks.
---

# Initialize skill
## Role

Act as a senior software engineer with expertise in setting up development environments for coding agents.

## Task

Define project paths and context for the agent's workflow. 
Generate or update the main agents instructions file [AGENTS.md](./AGENTS.md).

## Context

- The [Agents Instructions template](./AGENTS.template.md) for the main instructions file.

## Steps to follow:

### Step 1: Define paths and configuration

- [ ] Ask the user to confirm or set the following paths:
  - {Agents_Folder} : The folder for agent-related files. Ex: './.agents'.
  - {Product_Folder}: The folder for product-related files. Ex: './.product'.
  - {Source_folders}: The array of source code folders. Ex: ['/src'], ['/back','/front'].
  - {Business_Domain_Language}: The language code, and documentation. Ex: 'English', 'Spanish', etc.

### Step 2: Define system architecture and technology stack
- [ ] Tiers ans software architecture (e.g., frontend, backend, database).
- For each tier.
  - [ ] Main programming language and version.
  - [ ] Frameworks and libraries used in the project.
  - [ ] Testing, Storage, Security, and Monitoring solutions and strategies.
  - [ ] Development workflow: commands for init, build, run, test, lint, and deploy.
  - [ ] Folder structure and naming conventions for the source code.

### Step 3: Generate or update the AGENTS.md file
- [ ] Review the existing `AGENTS.md` if it exists.
- [ ] Create or update the `AGENTS.md` following the [Agents Instructions template](./AGENTS.template.md)
- [ ] Ensure defined paths, architecture, technology stack, and any specific guidelines are set.
- [ ] Keep it under 100 concise sentences.

## Output checklist

- [ ] The `AGENTS.md` is created or updated.
- [ ] The {Agents_Folder} and {Product_Folder} and {Source_folders} and {Business_Domain_Language} are set.
- [ ] The product overview is clearly defined.
- [ ] The technical details are defined for each tier.