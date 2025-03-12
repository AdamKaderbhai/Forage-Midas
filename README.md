# JPMC Forage Software Engineering Job Simulation
## Project-Midas

## TASK 1

JPMC wants to study how commercial behaviour naturally emerges in closed behaviour systems. To do this, they are running an experiment in Port Cornus, a small fishing community on the Alaskan Fringe. The denizens have consented to conduct all their local financial transactions through a new system called Midas. JPMC should be able to collect a relatively complete corpus of economic data regarding the community. This data is anticipated to reveal naturally emerging economic trends, which then can be extrapolated to the broader economic system JPMC operates within. The task is to develop the Midas system.

### Background Information

Your team has been asked to build out the Midas system, a high-profile, high-stakes project. The Midas system architecture has already been defined, and tasking has been distributed. You will be working on **Midas Core**—the component responsible for receiving, validating, and recording financial transactions. This component depends on:

- **Kafka** to receive new transactions
- **SQL Database** to record and validate them
- **REST API** to incentivize them

Your job is to integrate all these resources into the final system.

### Task Steps

1. **Fork and clone** the project repo: [GitHub Repo](https://github.com/vagabond-systems/forage-midas)
2. Open the program in your IDE (preferably IntelliJ).
3. Use **Java 17** and configure your IDE accordingly.
4. Familiarize yourself with the codebase.
5. Add the following dependencies to your **Spring project**:
   - `spring-boot-starter-data-jpa` (v3.2.5)
   - `spring-boot-starter-web` (v3.2.5)
   - `spring-kafka` (v3.1.4)
   - `h2` (v2.2.224)
   - `spring-boot-starter-test` (v3.2.5)
   - `spring-kafka-test` (v3.1.4)
   - `kafka` from `org.testcontainers` (v1.19.1)
6. Run `TaskOneTests` and submit the output snippet.

---

## TASK 2

### Background Information

Midas Core will process, validate, and record transactions. These transactions will come from a **web front-end, point of sale system, and mobile app**. These components have been **decoupled** from Midas Core using a **message queue** (Kafka).

### Task Steps

1. Implement a **Kafka listener** that:
   - Listens to a configured topic from `application.yml`
   - Deserializes incoming messages into the **Transaction class**
2. Run `TaskTwoTests` and submit the first four transaction amounts received.

---

## TASK 3

### Background Information

Now that transactions are flowing into the system, the next step is **validating and storing** them in an **SQL database** (H2). The transactions must meet the following conditions:

- **Valid sender ID**
- **Valid recipient ID**
- **Sender has a balance ≥ transaction amount**

If valid, the transaction is recorded, and balances are updated.

### Task Steps

1. Integrate **H2 database** into Midas Core.
2. Create a new `TransactionRecord` entity.
3. Implement validation logic.
4. Run `TaskThreeTests` and submit the **balance of "waldorf"** after processing.

---

## TASK 4

### Background Information

JPMC wants to study how **monetary incentives** influence purchasing decisions. They introduced an **Incentive API**, which determines additional amounts to be added to transactions. This incentive money comes from JPMC.

### Task Steps

1. Integrate the **Incentives API** (runs on `http://localhost:8080/incentive`).
2. Post validated transactions to the API.
3. Record the incentive amount and update the **recipient’s balance** accordingly.
4. Run `TaskFourTests` and submit the **balance of "wilbur"**.

---

## TASK 5

### Background Information

Users need an API to **query their account balances**. Midas Core will expose a **REST API** for this purpose.

### Task Steps

1. Implement a REST API endpoint `/balance`:
   - Accepts `userId` as a query parameter.
   - Returns a **Balance object** in JSON format.
   - Runs on port `33400`.
2. If a user does not exist, return `0` balance.
3. Run `TaskFiveTests` and submit the output.

