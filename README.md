# Supermarket

## Table of Contents

- [Description](#description)
- [Requirements](#requirements)
- [Project Configuration and Execution](#test)
- [Developer](#developer)  

## Description:

This project is a microservice for managing supermarkets and employees, enabling operations through a REST API. The application uses an in-memory H2 database along with Flyway migrations for data initialization


## Requirements

- **Java 17 or higher**
- **Maven**
- **Any IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.)
- **Postman** (to test REST APIs)
- **Git** (Optional to clone the repository)
- **Docker** (To add an additional service)


## Project Configuration and Execution

### 1. Clone Repository
Clone the Repository

```bash
git clone https://github.com/Albertomartin21/supermarkets.git
```

### 2. Configure the Environment

No additional configuration is required if you use the dependencies listed in the pom.xml file. Make sure you have internet access so Maven can download the required dependencies.


### 3. Compile and Run the Project

**IMPORTANT**: If you encounter versioning errors, remember that we are working with Java 17 or higher and Spring Boot 4.0.5. Therefore, you will need to change your configuration if it is not already set up correctly to test this project (for example, the JDK used by your Maven installation).


First we need to set up Docker Compose to retrieve information from stores use:
```bash
`docker compose up`
```

Secondly you can compile the project using Maven from the command line by navigating to the project directory:

```bash
`mvn clean install`
`mvn spring-boot:run`
```

### 4. Testing the Service with Postman or Swagger UI

- Swagger UI

Once the project is up and running, after completing step 3, access the URL http://localhost:8080/swagger-ui/index.html to directly test the different endpoints without using additional software.

- Postman

It is worth noting that we need the desktop version to be able to make requests to **localhost:8082**, as is our case.

The application exposes the following routes:

**Open access**
- POST /auth/register - Registration in the system.
- POST /auth/login - Login to the system.

**Access with authentication**
- GET /workers - Return all workers.
- GET /store/{idStore} - Return all workers from a store.
- GET /store/{dni} - Return worker by DNI
- GET /store/search - Return all workers by a name and lastName.
- POST /workers - Add a new worker.
- PUT /workers/{dni} - Update a worker.
- DELETE /workers/{dni} - Delele a worker by dni.
- POST /{dni}/assign - Assign section to worker
- POST /{dni}/unassign - Unassign section to worker

- GET /reports/{storeId} - Get the store report by identifier.
- GET /reports/remaining/{storeId} - Retrieve the missing store report by identifier.

- GET /stores/{storeId} - Get store information by identifier.


**Test**
To test i leave a collection of postman in the folder /postman to test the service.


**Testing notes**

POST /auth/register

- URL: http://localhost:8082/auth/register
- Body:
```bash
{
    "username": "user",
    "password": "password"
}
```

- Once we have successfully registered, we proceed to login.


POST /auth/login

- URL: http://localhost:8080/auth/login
- Body:
```bash
{
    "username": "user",
    "password": "password"
}
```
- Response:
```bash
Bearer Token
```

-It is **ESSENTIAL** to copy this token because for subsequent requests we need to be authenticated and pass this token in the header.

Authorization --> Bearer Token --> Paste the copied token.


For test api with worker:

**The available sections are: HORNO, CAJAS, PESCADERIA, VERDURAS, DROGUERIA**


```bash
{
  "dni": "12345678A", (dni from worker)
  "name": "Alberto", (name from worker)
  "lastNames": "Martin", (lastnames from worker)
  "mapSection": { (map with sections and hours that a worker is one day) 
    "DROGUERIA": 2 
  },
  "maxHours": "8", (Maximum hours worked in a day per contract)
  "idStore": 1 (Id from Store where he works)
}
```

### 5. Testing

The project includes unit tests for the WorkerServiceTest, StoreServiceTest and ReportServiceTest. To run the tests:

```bash
mvn test
```

### 6. Data base

The service uses an in-memory H2 database to store products and Flyway migrations for data initialization. This ensures that the data is automatically initialized each time the project starts.

**Database Configuration**
- The configuration can be found in **application.yaml**. To access the H2 console, you can enable it by going to:

```bash
URL: http://localhost:8082/h2-console
JDBC URL: jdbc:h2:mem:supermarket
Username: sa
Password: sa
```

### Proyect struct
It has been made with hexagonal architecture

```bash
src/
├── main/
│   ├── java/
│   │   └── com.mercadona.supermarket/
│   │       ├── application/ 
│   │       │     ├── mapper/  # Mapper between the infra layer and the domain layer 
│   │       │     └── usecases/ # Use cases from service                            
│   │       ├── domain/       
│   │       │      ├── cron/  # Cron to reset daily data
│   │       │      ├── exception/  # Exception handling 
│   │       │      ├── model/  # Data models 
│   │       │      ├── repository/  # Connectivity port between domain layer and infra layer
│   │       │      ├── rest/  # Connectivity port between domain layer and infra layer   
│   │       │      └── service/ # Coordinate the use case and business logic      
│   │       │
│   │       └── infraestructure/
│   │             ├── config/  # Security and database settings
│   │             ├── controller/  #REST drivers
│   │             ├── dtos/  # Data input and output models
│   │             ├── entity/ # Database models
│   │             ├── repository/ # JPA Repositories
│   │             └── rest/ # Requests to other services
│   └── resources/
│       ├── application.yaml # Spring Boot Configuration
│       └── db/migration/         # Flyway migration scripts
└── test/                         # Unit tests
```

## Developer
Alberto Martín Amengual:  
:octocat: [Albertomartin21](https://github.com/Albertomartin21)  
:envelope: alberto.mamengual@gmail.com 
