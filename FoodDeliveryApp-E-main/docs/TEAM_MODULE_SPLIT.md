# FoodDeliveryApp Team Module Split (17 Teams)

This project is organized as one shared codebase with team-owned modules. Each team should deliver one module.

## Common rules for every team
- Do not break public interfaces owned by other teams.
- Keep dependencies one-way through interfaces.
- Add unit tests for happy path and at least 2 failure/edge cases.
- Add at least 1 sequence diagram and 1 class diagram for your module.
- Document assumptions and integration points in your module README section.

## Team modules and ownership

| Team | Module | Primary package | Depends on |
|---|---|---|---|
| 1 | User Identity and Roles | `edu.classproject.user` | none |
| 2 | Auth Sessions | `edu.classproject.auth` | `user` |
| 3 | Customer Profile and Address Book | `edu.classproject.profile` | `user` |
| 4 | Restaurant Registry and Menu | `edu.classproject.restaurant` | none |
| 5 | Search and Discovery | `edu.classproject.search` | `restaurant` |
| 6 | Cart Management | `edu.classproject.cart` | `restaurant` |
| 7 | Pricing and Promotions | `edu.classproject.pricing` + future `promo` rules | `cart` |
| 8 | Checkout and Payment | `edu.classproject.payment` | `pricing`, `user` |
| 9 | Order Orchestration | `edu.classproject.order` | `restaurant`, `payment`, `notification` |
| 10 | Restaurant Order Board | `edu.classproject.restaurantops` | `order` |
| 11 | Delivery Partner Management | `edu.classproject.delivery` | none |
| 12 | Dispatch Assignment | `edu.classproject.dispatch` | `delivery`, `order` |
| 13 | Live Tracking Timeline | `edu.classproject.tracking` | `order`, `dispatch` |
| 14 | Notifications | `edu.classproject.notification` | `order`, `tracking` |
| 15 | Ratings and Reviews | `edu.classproject.review` | `order`, `restaurant` |
| 16 | Support and Refunds | `edu.classproject.support` | `order`, `payment` |
| 17 | Admin Analytics and Reporting | `edu.classproject.analytics` | all read-only modules |

## Acceptance criteria by team

### Team 1: User Identity and Roles
- Can register at least `CUSTOMER`, `RESTAURANT_OWNER`, `DRIVER` users.
- Duplicate email validation exists.
- Unit tests cover not-found and duplicate-user cases.

### Team 2: Auth Sessions
- `AuthService` supports login, logout, and session validation.
- Session expiry behavior is implemented and tested.
- Invalid credentials do not create sessions.

### Team 3: Customer Profile and Address Book
- Profile upsert works for existing users only.
- At least 2 addresses per user are supported.
- Address validation rules are documented and tested.

### Team 4: Restaurant Registry and Menu
- Restaurant creation and menu item CRUD work.
- Menu item price must be positive.
- Tests cover missing restaurant and missing menu item behavior.

### Team 5: Search and Discovery
- Restaurant search by name is case-insensitive.
- Returns stable ordering and empty list when no match.
- At least one ranking rule is implemented and explained.

### Team 6: Cart Management
- Can create cart and add/update/remove lines.
- Single cart cannot contain items from multiple restaurants.
- Quantity validation and clear-cart flow are tested.

### Team 7: Pricing and Promotions
- Price breakdown includes subtotal, delivery fee, tax, discount, total.
- Invalid coupon does not crash checkout; returns zero discount.
- Rounding behavior for currency is documented and tested.

### Team 8: Checkout and Payment
- Payment request includes user and total amount.
- Decline path returns clear failure reason.
- Mock gateway adapter is replaceable via interface.

### Team 9: Order Orchestration
- Order creation validates restaurant and item references.
- Status transition rules are enforced (no arbitrary jumps).
- Payment success/failure paths are both tested.

### Team 10: Restaurant Order Board
- Restaurant can fetch active orders.
- Board supports prepare and ready-for-pickup updates.
- Illegal transitions are rejected and tested.

### Team 11: Delivery Partner Management
- Register partner and update availability supported.
- `getAvailablePartners` only returns available partners.
- Tests include repeated toggles and unknown partner behavior.

### Team 12: Dispatch Assignment
- Assignment chooses only available partner.
- If no partner available, dispatch fails with explicit reason.
- Assignment decision rule is documented and tested.

### Team 13: Live Tracking Timeline
- Timeline stores events ordered by timestamp.
- At least 5 order milestones are supported.
- Querying unknown order returns empty timeline.

### Team 14: Notifications
- Notification sent for order confirm, payment fail, out-for-delivery, delivered.
- Notification channel abstraction exists (console/mock/email stub).
- Delivery failure is handled without crashing core flow.

### Team 15: Ratings and Reviews
- Review submission requires completed order.
- Rating must be in range 1..5.
- Restaurant review listing returns aggregate average.

### Team 16: Support and Refunds
- Ticket creation supports at least 3 issue types.
- Ticket workflow states are enforced.
- Refund decision path (approve/reject) is auditable.

### Team 17: Admin Analytics and Reporting
- Generates daily summary metrics (orders, GMV, failures, avg delivery time).
- Read-only aggregation over module data sources.
- Report generation is deterministic and tested.

## Definition of done for all teams
- Module compiles and tests pass with `mvn test`.
- API contract is documented in code (`interface` + request/response types).
- Team demo scenario is included in a small test or bootstrap flow.
