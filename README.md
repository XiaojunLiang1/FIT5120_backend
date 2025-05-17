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
<pre> FIT5120_backend/ ├── .github/workflows/ # GitHub Actions for CI/CD deployment │ └── deploy.yml ├── .mvn/wrapper/ # Maven wrapper scripts ├── src/ │ └── main/ │ └── java/ │ └── com/fit5120/backend/ │ ├── controller/ # REST API controllers │ ├── service/ # Business logic │ ├── model/ # DTOs and domain objects │ └── repository/ # Data handling (if used) │ │ └── resources/ │ ├── application.properties # Spring Boot config │ └── static/ or templates/ # (if frontend or docs included) ├── pom.xml # Project dependencies and build config ├── mvnw / mvnw.cmd # Maven wrapper executables ├── .gitignore / .gitattributes # Git-related configs └── README.md # Project documentation </pre>

### Prerequisites

- Java 17+
- Maven 3.x

### Run Locally

```bash
./mvnw spring-boot:run
