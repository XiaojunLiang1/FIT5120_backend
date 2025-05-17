# FIT5120 TP19 Heatwise Connect – Backend API

This is the backend service for the FIT5120 project, which supports a heatwave preparedness platform aimed at helping elderly and at-risk populations stay safe during extreme summer conditions in Australia.

The backend is built with **Spring Boot**, exposes multiple REST APIs, and is deployed via **AWS EC2 + AWS API Gateway**.

---
## Key Features

Live Weather APIs

- Get current temperature and “feels like” temperature by geo coordinates or postcode

- Multi-day weather forecasts based on user location


Location Utilities

- Address auto-suggestions from partial input (suburb, keyword, etc.)

- Retrieve and display drinking fountain locations across Melbourne

Food Safety Intelligence

- Get food storage risk and recommendations based on item + location temperature

- Temperature-sensitive spoilage info for fruits and vegetables

- Retrieve grouped food items by category (e.g., Fruit, Vegetable)

News Integration

- Fetch up-to-date heatwave-related news articles with images and summaries

Personalized Packing List Generator

- Generates a recommended summer packing list based on: （Age group, Health status, Residential disadvantage level (SEIFA-based))

## Project Structure
FIT5120_backend/
├── src/
│ └── main/java/com/fit5120/backend/
│ ├── controller/ # REST controllers
│ ├── service/ # Business logic
│ ├── model/ # DTOs and data structures
│ └── repository/ # Data source 
├── .github/workflows/ # GitHub Actions for deployment
├── pom.xml # Maven dependencies

### Prerequisites

- Java 17+
- Maven 3.x

### Run Locally

```bash
./mvnw spring-boot:run
