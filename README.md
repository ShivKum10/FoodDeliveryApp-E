# FoodDeliveryApp

Framework-free Java starter for an Object Oriented Analysis and Design mini-project.

## Stack
- Java 17+
- Maven
- JUnit 5
- In-memory repositories/adapters

## Current structure
- Core shared: `src/main/java/edu/classproject/common`
- Existing implemented modules:
  - `user`
  - `restaurant`
  - `order`
  - `payment`
- Team starter contract modules:
  - `auth`, `profile`, `search`, `cart`, `pricing`, `restaurantops`,
  - `delivery`, `dispatch`, `tracking`, `review`, `support`, `analytics`
  - `notification` (interface-only; team-owned implementation)

## Team-ready planning
- Full 17-team split and acceptance criteria:
  - `docs/TEAM_MODULE_SPLIT.md`

## Build and test
```bash
mvn test
```

## Run demo
```bash
mvn -q exec:java -Dexec.mainClass=edu.classproject.bootstrap.DemoApplication
```

## Suggested extension rules
1. Depend on interfaces, not implementations.
2. Keep domain entities free of infra concerns.
3. Add tests for every behavior change.
4. Keep module APIs explicit and stable.
