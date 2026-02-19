# 🎟️ TicketMaster Core Backend

A **Spring Boot–based high‑concurrency event booking engine** designed to prevent double‑booking during peak traffic while supporting dynamic pricing and audit tracking.

---

## 📌 Project Overview

During popular event sales, multiple users may attempt to book the same seat simultaneously. This backend system solves that problem using **database locking, time‑bound seat holds, strategy‑based pricing**, and **AOP auditing**.

The project is designed as an **industry‑style backend system** focusing on concurrency, clean architecture, and extensibility.

---

## ⚙️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA (Hibernate)**
* **Spring AOP**
* **MySQL**
* **Maven**
* **Postman** (API testing)

---

## 🚀 Core Features

### 1️⃣ 10‑Minute Seat Lock Strategy (Concurrency Control)

* Endpoint: `POST /seats/{id}/hold`
* Seats can be **AVAILABLE, HELD, or SOLD**
* When a seat is held:

  * Status changes to `HELD`
  * `hold_expiry` is set to **current time + 10 minutes**
* If another user attempts to hold the same seat:

  * Expired hold → overwritten
  * Active hold → `SeatLockedException` thrown with remaining time
* Implemented using **Optimistic or Pessimistic Database Locking**

---

### 2️⃣ VIP Tiered Pricing Engine (Strategy Pattern)

Pricing is calculated dynamically based on **User Tier** and **Event Demand**.

| User Type | Pricing Rule                               |
| --------- | ------------------------------------------ |
| REGULAR   | Pays base price                            |
| VIP       | 10% discount unless event is HIGH_DEMAND   |
| PLATINUM  | Pays base price + receives priority access |

✔ Implemented using **Java Interfaces / Strategy Pattern** for extensibility.

---

### 3️⃣ Audit Shadow (Spring AOP)

* Custom annotation: `@AuditFailure`
* Applied to booking service methods
* Any exception triggers:

  * User ID logging
  * Timestamp logging
  * Failure reason logging
* Stored in a dedicated **audit_logs** MySQL table

## 🧪 How to Run

1. Clone the repository
2. Configure MySQL credentials in `application.yml`
3. Run the application

   ```bash
   mvn spring-boot:run
   ```
4. Import the Postman collection and test concurrent seat holds

## 👨‍💻 Author

**Hiranya Mendis**
Backend Developer | Spring Boot

---

⭐ If you find this project useful, feel free to star the repository!
