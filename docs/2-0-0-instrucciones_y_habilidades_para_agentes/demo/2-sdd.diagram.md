# Level 2 SDD workflow

```mermaid
flowchart TD
  classDef nd fill:#f8fafc,stroke:#00c4cc,color:#457b9d
  classDef sg fill:#f1f5f9,stroke:#00f2ff,color:#457b9d 

  subgraph P["PRODUCT"]
      REQ["*.spec.md"]:::nd
  end

  subgraph A["AGENTS"]
      AGT["AGENTS.md"]:::nd
      SKL["skills/"]:::nd
      AGT -.-> SKL
  end  

  subgraph S["SOLUTION"]
      COD[Source Code]:::nd
  end

	HMN -->|/initialize| AGT
  HMN -->|/write-a-skill| SKL
  REQ -->|/codify| COD
  AGT -.-> COD  

  class P,A,S sg
```

## Commands

- `/initialize` - Create initial technology documentation (/AGENTS.md and skills/) for a project.

- `/codify` - Run the implementation cycle for one specification: generate plans, produce code, and validate with tests.

- `/write-a-skill` - Create a new skill from a human need (Can be a rule set, a workflow, or a utility command).

## Artifacts

- `/AGENTS.md` - The entry point for any agent joining the project; defines how agents should operate, including rules, workflows, and artifact conventions.

- `skills/` - Teach your agent how to do things. Make them easy to know when to use.

- `Source Code` - The implementation of the system, including unit tests.
