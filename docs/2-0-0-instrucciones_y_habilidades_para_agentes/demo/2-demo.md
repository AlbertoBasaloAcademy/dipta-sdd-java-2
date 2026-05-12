# Session 2.

## 1. Commands

### 1.1 Codify as a command prompt

> `/.agents/prompts/codify.prompt.md` or `/.agents/commands/codify.md`

```yaml
---
name : codify
description : Write code to implement a feature.
parameters : A request or a specification file with a feature or bug to fix.
---
```

- [codify.md](../../1-0-0-ecosistema_y_vibraciones/demo/assets/1-codify.md) contains the detailed guidelines for the codify command prompt.

---

## 2. Initialize

### 2.1 AGENTS.md to rule them all

> `initialize this project`

```yaml
---
name : initialize
description : Initialize the environment by loading all necessary commands, rules, and skills. This should be the first skill activated to set up the context for subsequent tasks.
---
```

- [initialize.md](./assets/initialize/SKILL.md) to be copied to `/.agents/skills/initialize/SKILL.md`.

- [AGENTS.template.md](./assets/initialize/AGENTS.template.md) to be copied to `/.agents/skills/initialize/AGENTS.template.md`.

### 2.2 Language rules

> `/.agents/instructions/java.instructions.md` or `/.agents/rules/java.md`

```yaml
---
name : java
description : Java coding standards for Spring Boot services; naming, immutability, Optional usage, streams, exceptions, generics, and project layout. 
applyTo : back/**/*.java
---
```

- [java.md](./assets/java.md) contains the detailed coding rules for Java in Spring Boot services.

---



## 3. Skills

### 3.1 Refactor as a skill set

> `codify this requirement or implement this specification file`

```yaml
---
name : codify
description : Write code to implement a feature, following the codify command prompt guidelines. To be used to implement features or fix bugs ensuring code quality and maintainability.
---
```

> `follow java rules`

```yaml
---
name : java
description : Java coding standards for Spring Boot services; naming, immutability, Optional usage, streams, exceptions, generics, and project layout. To be used as a reference for writing or reviewing Java code in the back-end service, ensuring consistency and adherence to best practices.
---
```

- [java.md](./assets/java.md) to be copied to `/.agents/skills/java/SKILL.md`.

### 3.2 Fallbacks

- ClaudeCode, Codex, Cursor and Copilot can read skills and use them automatically.
- With IntelliJ, you can mention the `@skill-name/SKILL.md` in the prompt to use it.


### 3.3 Find skills

- **Marketplaces**:
  - [From Vercel](https://skills.sh/)
  - [From Awesome Copilot](https://awesome-copilot.github.com/skills/)
  - [From Anthropic](https://github.com/anthropics/skills)
  - [From OpenAI](https://github.com/openai/skills)
  - [From Addy Osmani](https://github.com/addyosmani/agent-skills)
  - [From Matt Pocock](https://github.com/mattpocock/skills)

- and... [The Find Skills skill](https://skills.sh/vercel-labs/skills/find-skills)

### 3.4 Write a skill

> `write-a-skill for some task`.

- [write-a-skill.md](https://github.com/mattpocock/skills/blob/main/skills/productivity/write-a-skill/SKILL.md) to be copied to `/.agents/skills/write-a-skill/SKILL.md`.

- [Skill Creator](https://github.com/anthropics/skills/tree/main/skills/skill-creator) to be copied to `/.agents/skills/skill-creator/`.