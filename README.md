# Spring Boot Boilerplate

A production-ready Spring Boot 4.0.0 application with JWT authentication, PostgreSQL 16, Flyway migrations, and hot reload support.

## Overview

- **Framework**: Spring Boot 4.0.0 with Spring Security 6.5
- **Authentication**: JWT token-based (60-minute expiration)
- **Database**: PostgreSQL 16 with Hibernate ORM & Flyway migrations
- **Hot Reload**: Spring Boot DevTools with Gradle continuous build
- **API Documentation**: OpenAPI 3.0 with Swagger UI
- **Container**: Docker & Docker Compose
- **Java**: Version 21 (LTS)

## Features

✅ User Registration & Login with JWT  
✅ Password Hashing (BCrypt)  
✅ Role-Based Access Control (ROLE_USER, ROLE_ADMIN)  
✅ Global Exception Handling & Input Validation  
✅ Health Check Endpoints (no auth required)  
✅ PostgreSQL with Flyway schema migrations  
✅ Hot Reload Development (Spring Boot DevTools)  
✅ MapStruct type-safe DTO mapping  
✅ Swagger/OpenAPI documentation  
✅ Environment Configuration via .env  

## Project Structure (Initial Setup)

```
springboot-boilerplate/
├── src/main/java/com/conglt/learning/springbootboilerplate/
│   ├── configuration/              # Spring Security, Password Encoder, Swagger
│   ├── controller/                 # REST endpoints (Health, Hello, Login, Register)
│   ├── model/                      # JPA entities (User, UserRole)
│   ├── repository/                 # JPA repositories
│   ├── security/                   # JWT & authentication
│   │   ├── dto/                   # Request/Response DTOs
│   │   ├── jwt/                   # JWT token provider & filter
│   │   ├── mapper/                # MapStruct mappers
│   │   └── service/               # Business logic (UserService)
│   ├── exceptions/                 # Global exception handlers
│   └── SpringbootBoilerplateApplication.java
│
├── src/main/resources/
│   ├── application.properties       # Configuration
│   └── db/migration/               # Flyway SQL migrations
│       ├── V1.0__Create_users_table.sql
│       ├── V1.1__Create_audit_log_table.sql
│       └── V1.2__Create_postgresql_extensions.sql
│
├── build.gradle                    # Gradle dependencies (Spring Boot DevTools)
├── docker-compose.yml              # PostgreSQL service
├── .env                            # Environment variables
└── scripts/
    └── init-db.sql                 # Database initialization
```

## Quick Start

### Prerequisites
- Docker & Docker Compose installed
- Or: Java 21, PostgreSQL 16 (for local development)

### Step 1: Create .env File
```bash
cp .env.example .env
```

### Step 2: Start PostgreSQL
```bash
docker-compose up -d postgres
```

### Step 3: Build & Run Application

**With Hot Reload (Development):**
```bash
./gradlew bootRun -t
```

**Regular Run:**
```bash
./gradlew bootRun
```

### Step 4: Verify & Access

**Health Check:**
```bash
curl http://localhost:8089/health
```

**Swagger UI:**
```
http://localhost:8089/swagger-ui.html
```

## Build from Source

### Prerequisites
```bash
java -version          # Java 21+
gradle -version        # Gradle 9.2.1+
docker -version        # Docker 20.10+ (for PostgreSQL)
```

### Build Steps

**1. Build Project:**
```bash
./gradlew clean build -x test
```

**2. Start PostgreSQL:**
```bash
docker-compose up -d postgres
```

**3. Run with Hot Reload:**
```bash
./gradlew bootRun -t
```

**4. Test API:**
```bash
# Health check
curl http://localhost:8089/health

# Register
curl -X POST http://localhost:8089/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"Test@1234","firstName":"Test","lastName":"User"}'

# Login
curl -X POST http://localhost:8089/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"Test@1234"}'

# Protected endpoint (use JWT token from login)
curl -X GET http://localhost:8089/hello \
  -H "Authorization: Bearer <JWT_TOKEN>"
```
---

**Version**: 1.0.0 | **Java**: 21 | **Spring Boot**: 4.0.0  
**Status**: ✅ Production Ready

Quick Start: `docker-compose up -d postgres` → `./gradlew bootRun -t` → http://localhost:8089/swagger-ui.html

