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

## Project Structure

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

### Step 1: Start PostgreSQL
```bash
docker-compose up -d postgres
```

### Step 2: Build & Run Application

**With Hot Reload (Development):**
```bash
./gradlew bootRun -t
```

**Regular Run:**
```bash
./gradlew bootRun
```

### Step 3: Verify & Access

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

## API Endpoints

### Public (No Authentication)
| Method | Path | Description |
|--------|------|-------------|
| GET | `/health` | Health check status |
| POST | `/register` | Register new user |
| POST | `/login` | Login & get JWT token |

### Protected (JWT Required)
| Method | Path | Description |
|--------|------|-------------|
| GET | `/hello` | Test protected endpoint |

### Documentation
| Path | Description |
|------|-------------|
| `/swagger-ui.html` | Interactive API docs |
| `/v3/api-docs` | OpenAPI JSON spec |
| `/actuator/health` | Actuator health endpoint |

## Docker Compose

```bash
docker-compose up -d postgres              # Start PostgreSQL
docker-compose down                        # Stop all services
docker-compose logs -f postgres            # View logs
docker-compose ps                          # Service status
```

## Configuration

### Environment Variables (.env)
```properties
SPRING_PROFILE=local
PORT=8089
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=springboot_boilerplate
POSTGRES_USER=pguser
POSTGRES_PASSWORD=pgpassword
DB_SCHEMA=public
JWT_SECRETKEY=your-secret-key
JWT_ISSUER=springboot-boilerplate
JWT_EXPIRATIONMINUTE=60
```

### Application Properties
Uses environment variables from `.env`:
```properties
server.port=${PORT:8089}
spring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
spring.flyway.enabled=true
jwt.secretKey=${JWT_SECRETKEY}
```

## Technology Stack

**Backend**: Spring Boot 4.0.0, Spring Security 6.5, Spring Data JPA  
**Database**: PostgreSQL 16, Hibernate ORM, Flyway migrations  
**Security**: JWT (Auth0), BCrypt password hashing  
**API**: OpenAPI 3.0, Swagger UI, Spring Doc  
**Mapping**: MapStruct (type-safe DTO conversion)  
**Logging**: Logback, SLF4J  
**Build**: Gradle 9.2.1, Spring Boot DevTools  
**Container**: Docker, Docker Compose  
**Java**: Version 21 LTS  

## Troubleshooting

### Port already in use
```bash
lsof -ti :8089 | xargs kill -9
```

### Database connection failed
```bash
docker-compose restart postgres
docker-compose logs postgres
```

### Hot reload not working
```bash
# Use Gradle continuous build flag:
./gradlew bootRun -t

# This enables auto-compilation and auto-restart
```

### Flyway migrations failed
```bash
docker-compose logs postgres
# Check if PostgreSQL is healthy
docker-compose ps postgres
```

---

**Version**: 1.0.0 | **Java**: 21 | **Spring Boot**: 4.0.0  
**Status**: ✅ Production Ready

Quick Start: `docker-compose up -d postgres` → `./gradlew bootRun -t` → http://localhost:8089/swagger-ui.html

