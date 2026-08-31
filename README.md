# Journal Hub

A backend REST API for a personal journaling application built with Java and Spring Boot. Journal Hub allows users to securely create, manage, update, and delete their personal journal entries while keeping user data isolated through authentication and authorization. 

The project is designed to demonstrate practical backend development concepts including Spring Boot, REST APIs, MongoDB, Spring Security, Redis caching, email services, scheduled tasks, and layered application architecture.

## Features

- User authentication and authorization using **Spring Security**
- Create, read, update, and delete journal entries
- User management
- Role-based admin operations
- Redis Cloud caching for improved performance
- Email service using **Spring Boot Mail**
- Scheduled background tasks
- MongoDB for data persistence
- Layered architecture using Controller, Service, Repository, and Entity layers

## Architecture

The project follows a layered Spring Boot architecture:

```text
journal-hub
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.nidhiP.journalApp
│   │   │       │
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── entity
│   │   │       ├── enums
│   │   │       ├── repository
│   │   │       ├── scheduler
│   │   │       ├── services
│   │   │       └── JournalApplication
│   │   │
│   │   └── resources
│   │
│   └── test
│
├── .github
│   └── workflows
│
├── .mvn
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 17** | Programming language |
| **Spring Boot** | Backend framework |
| **Spring Web** | REST API development |
| **Spring Security** | Authentication & authorization |
| **Spring Data MongoDB** | Database access |
| **MongoDB** | Primary database |
| **Redis Cloud** | Caching |
| **Spring Cache** | Cache management |
| **Spring Boot Mail** | Email service |
| **Lombok** | Reduce boilerplate code |
| **Maven** | Build & dependency management |
| **JUnit / Spring Boot Test** | Testing |

## Getting Started

You can verify Java: java -version

Verify Maven: mvn -version

### 1. Clone the Repository
git clone https://github.com/NidhiPednekar/journal-hub.git

Navigate into the project: cd journal-hub

### 2. Configure Environment Variables

Create a .env file in the project root and add your MongoDB, Redis, and email configuration. ex:

MONGODB_URI=mongodb+srv://YOUR_USERNAME:YOUR_PASSWORD@cluster0.osoiliq.mongodb.net/journalApp?appName=Cluster0
MAIL_USERNAME=YOURMAIL@GMAIL.COM
MAIL_PASSWORD=YOUR_MAIL_APP_PASSWORD
REDIS_URL=redis://:<password>@<host>:<port>

### 3. Start MongoDB

Make sure MongoDB is running locally or use a MongoDB cloud provider.

For a local MongoDB installation, the default connection is commonly: mongodb://localhost:27017

### 4. Run the Application

Using Maven: ./mvnw spring-boot:run

On Windows: mvnw.cmd spring-boot:run

Or using installed Maven: mvn spring-boot:run

### 5. Running Tests

Run the test suite using: ./mvnw test

On Windows: mvnw.cmd test

The project includes a test source tree under: src/test/java

### REST API Endpoints

### Journal Entries

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/journal` | Get user's journal entries |
| `POST` | `/journal` | Create a journal entry |
| `GET` | `/journal/id/{id}` | Get a journal entry |
| `PUT` | `/journal/id/{id}` | Update a journal entry |
| `DELETE` | `/journal/id/{id}` | Delete a journal entry |

### User

| Method | Endpoint | Description |
|--------|----------|-------------|
| `PUT` | `/user` | Update user information |
| `DELETE` | `/user` | Delete user account |

### Admin

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/admin/all-users` | Get all users |
| `POST` | `/admin/create-admin-user` | Create an admin user |

### Create Journal Entry
POST /journal
Content-Type: application/json
Authorization: Bearer <JWT>
{
  "title": "My First Journal Entry",
  "content": "Today I started working on my Spring Boot project."
}

## Author

**Nidhi Pednekar**

[GitHub](https://github.com/NidhiPednekar)
