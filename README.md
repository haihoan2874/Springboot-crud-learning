# Spring Boot Boilerplate

A production-ready Spring Boot application with JWT authentication, PostgreSQL, Redis, and Docker support.

## Overview

- **Framework**: Spring Boot 4.0.0 with Spring Security
- **Authentication**: JWT token-based (60-minute expiration)
- **Database**: PostgreSQL 16 with Hibernate ORM
- **Cache**: Redis 7
- **API Documentation**: Swagger/OpenAPI 3.0
- **Container**: Docker & Docker Compose (8 services)
- **Java**: Version 21

## Features

✅ User Registration & Login with JWT  
✅ Password Hashing (BCrypt - Strength 12)  
✅ Role-Based Access Control (ROLE_USER, ROLE_ADMIN)  
✅ Global Exception Handling & Input Validation  
✅ Health Check Endpoints (no auth required)  
✅ PostgreSQL Integration with Hibernate  
✅ Redis Caching & Session Management  
✅ Mailhog Email Testing  
✅ LocalStack AWS Services (S3, SQS, SNS, DynamoDB)  
✅ Swagger/OpenAPI Documentation  
✅ Environment Configuration via .env  

## Project Structure

```
project-root/
├── src/main/java/com/conglt/learning/springbootboilerplate/
│   ├── configuration/          # Spring configs (Security, JWT, Swagger)
│   ├── controller/             # REST controllers (Login, Register, Health)
│   ├── model/                  # JPA entities (User, UserRole)
│   ├── repository/             # JPA repositories
│   ├── security/               # JWT & authentication
│   │   ├── dto/               # Request/Response DTOs
│   │   ├── jwt/               # JWT token provider & filter
│   │   ├── mapper/            # MapStruct mappers
│   │   └── service/           # Business logic
│   └── exceptions/             # Global exception handlers
├── src/main/resources/
│   ├── application.properties   # Server config (uses env variables)
│   └── messages/              # i18n messages
├── Dockerfile                  # Multi-stage Docker build
├── docker-compose.yml          # 8 services (App, DB, Redis, etc.)
├── .env                        # Environment variables
└── scripts/
    ├── init-db.sql            # PostgreSQL initialization
    └── localstack-init.sh     # AWS services setup
```

## Quick Start

### Prerequisites
- Docker & Docker Compose installed
- Or: Java 21, PostgreSQL 16, Redis 7 (for local development)

### Step 1: Setup Environment
```bash
cd /home/lam.tuan.cong@sun-asterisk.com/learns/java/springboot-boilerplate
cp .env.example .env
```

Customize `.env` if needed (default values work for Docker):
```properties
PORT=8080
POSTGRES_PASSWORD=postgres
JWT_SECRETKEY=your-secret-key
```

### Step 2: Build & Start Services

**Using Docker Compose (Recommended):**
```bash
docker-compose up -d
```

**Using Make:**
```bash
make docker-up
```

**Check Status:**
```bash
docker-compose ps
```

### Step 3: Verify & Access

**Health Check:**
```bash
curl http://localhost:8080/api/health/ping
```

**Access Applications:**
| Service | URL | Credentials |
|---------|-----|-------------|
| API | http://localhost:8080 | - |
| Swagger UI | http://localhost:8080/swagger-ui.html | - |
| pgAdmin | http://localhost:5050 | admin@example.com / admin |
| Redis Commander | http://localhost:8081 | - |
| Mailhog | http://localhost:8025 | - |

## Build from Source

### Prerequisites
```bash
java -version          # Java 21+
gradle -version        # Gradle 9.2.1+
docker -version        # Docker 20.10+
```

### Build Steps

**1. Clean & Build:**
```bash
./gradlew clean build -x test
```

**2. Build Docker Image:**
```bash
docker-compose build
```

**3. Start All Services:**
```bash
docker-compose up -d
```

**4. Wait for Services (10-15 seconds):**
```bash
docker-compose logs -f app
# Wait for: "Tomcat started on port(s): 8080"
```

**5. Test API:**
```bash
# Register
curl -X POST http://localhost:8080/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"Test@1234"}'

# Login (get JWT token)
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"Test@1234"}'

# Access protected endpoint
curl -X GET http://localhost:8080/hello \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

## API Endpoints

### Public (No Authentication)
| Method | Path | Description |
|--------|------|-------------|
| POST | `/register` | Register new user |
| POST | `/login` | Login & get JWT token |
| GET | `/api/health` | Health status |
| GET | `/api/health/ping` | Simple ping |
| GET | `/api/health/detailed` | Detailed health info |

### Protected (JWT Required)
| Method | Path | Description |
|--------|------|-------------|
| GET | `/hello` | Test protected endpoint |

### Documentation
| Path | Description |
|------|-------------|
| `/swagger-ui.html` | Interactive API docs |
| `/v3/api-docs` | OpenAPI JSON spec |
| `/actuator/health` | Application health |

## Management Commands

### Using Make
```bash
make help                 # Show all commands
make docker-up           # Start services
make docker-down         # Stop services
make docker-logs-app     # View app logs
make docker-health       # Check health
make docker-db-connect   # Connect to PostgreSQL
make docker-redis        # Connect to Redis
```

### Using Docker Compose
```bash
docker-compose up -d                           # Start
docker-compose down                            # Stop
docker-compose logs -f app                     # Logs
docker-compose ps                              # Status
docker-compose exec postgres psql -U postgres  # DB shell
```

## Configuration

### Application Properties
Uses environment variables from `.env`:
```properties
# Database
spring.datasource.url=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}

# JWT
jwt.secretKey=${JWT_SECRETKEY}

# Logging
logging.level.root=${LOGGING_LEVEL_ROOT}
```

## Technology Stack

**Backend**: Spring Boot 4.0.0, Spring Security, Spring Data JPA  
**Database**: PostgreSQL 16, Hibernate ORM  
**Cache**: Redis 7  
**Security**: JWT (Auth0), BCrypt  
**API**: OpenAPI 3.0, Swagger UI  
**Tools**: Docker, Gradle, Makefile  

## Troubleshooting

### Port already in use
```bash
lsof -i :8080
kill -9 <PID>
```

### Database connection failed
```bash
docker-compose restart postgres
docker-compose logs postgres
```

### Application won't start
```bash
docker-compose logs app
docker-compose down -v
docker-compose up -d
```

---

**Version**: 1.0.0 | **Java**: 21 | **Spring Boot**: 4.0.0  
**Status**: ✅ Production Ready

Quick Start: `docker-compose up -d` → http://localhost:8080/swagger-ui.html

