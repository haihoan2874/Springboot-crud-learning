# 📦 Inventory Management System

A production-ready Spring Boot 4.0.0 application for managing inventory, categories, and products with JWT authentication, PostgreSQL 16, Flyway migrations, Thymeleaf UI, and hot reload support.

## 🎯 Overview

- **Framework**: Spring Boot 4.0.0 with Spring Security 6.5
- **Authentication**: JWT token-based (60-minute expiration)
- **Database**: PostgreSQL 16 with Hibernate ORM & Flyway migrations
- **Frontend**: Thymeleaf templates with Tailwind CSS & Font Awesome
- **Hot Reload**: Spring Boot DevTools with Gradle continuous build
- **API Documentation**: OpenAPI 3.0 with Swagger UI
- **Container**: Docker & Docker Compose
- **Java**: Version 21 (LTS)

## ✨ Features

### Core Features
✅ User Registration & Login with JWT  
✅ Password Hashing (BCrypt)  
✅ Role-Based Access Control (ROLE_USER, ROLE_ADMIN)  
✅ Global Exception Handling & Input Validation  

### Inventory Management
✅ Product Management (Create, Read, Update, Delete)  
✅ Category Management (Create, Read, Update, Delete)  
✅ Stock Tracking & Inventory Levels  
✅ Product List Web Interface (Thymeleaf + Tailwind CSS)  
✅ Audit Logging (User actions tracking)  
✅ Responsive Design (Mobile-friendly)

### Technical Features
✅ Health Check Endpoints (no auth required)  
✅ PostgreSQL with Flyway schema migrations  
✅ Hot Reload Development (Spring Boot DevTools)  
✅ MapStruct type-safe DTO mapping  
✅ Swagger/OpenAPI documentation  
✅ Environment Configuration via .env  
✅ RESTful API design  
✅ Request/Response DTOs with validation

---

## 📂 Project Structure

```
SpringBoot_CRUD/
├── src/main/java/com/conglt/learning/springbootboilerplate/
│   ├── SpringbootBoilerplateApplication.java    # Main application class
│   ├── configuration/                           # Spring configurations
│   │   ├── MessageConfiguration.java            # i18n & ObjectMapper bean
│   │   ├── PasswordEncoderConfiguration.java    # BCrypt configuration
│   │   ├── SecurityConfiguration.java           # Spring Security & JWT
│   │   └── SwaggerConfiguration.java            # OpenAPI/Swagger
│   ├── controller/                              # REST & Web controllers
│   │   ├── CategoryController.java              # Category REST API
│   │   ├── ProductController.java               # Product REST API
│   │   ├── ProductWebController.java            # Product Web UI (Thymeleaf)
│   │   ├── LoginController.java                 # Authentication endpoints
│   │   ├── RegistrationController.java          # User registration
│   │   ├── HealthController.java                # Health check
│   │   └── HelloController.java                 # Example endpoint
│   ├── model/                                   # JPA entities
│   │   ├── User.java                           # User entity
│   │   ├── Category.java                       # Product category
│   │   ├── Product.java                        # Product entity
│   │   ├── AuditLog.java                       # Audit logging
│   │   └── UserRole.java                       # User roles
│   ├── repository/                              # JPA repositories
│   │   ├── UserRepository.java
│   │   ├── CategoryRepository.java
│   │   ├── ProductRepository.java
│   │   └── AuditLogRepository.java
│   ├── service/                                 # Business logic
│   │   ├── UserService.java
│   │   ├── ProductService.java
│   │   ├── CategoryService.java
│   │   └── AuditLogService.java
│   ├── security/                                # Security & JWT
│   │   ├── dto/                                # Security DTOs
│   │   │   ├── LoginRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   └── RegisterRequest.java
│   │   ├── jwt/                                # JWT utilities
│   │   │   ├── JwtTokenProvider.java
│   │   │   ├── JwtAuthenticationFilter.java
│   │   │   └── JwtAuthenticationEntryPoint.java
│   │   └── mapper/                             # MapStruct mappers
│   │       └── UserMapper.java
│   └── exceptions/                              # Exception handling
│       ├── GlobalExceptionHandler.java
│       ├── ResourceNotFoundException.java
│       └── UnauthorizedException.java
│
├── src/main/resources/
│   ├── application.properties                  # Main configuration
│   ├── db/migration/                           # Flyway migrations
│   │   ├── V1.0__Create_users_table.sql       # Users table
│   │   ├── V1.1__Create_audit_log_table.sql   # Audit log table
│   │   ├── V1.2__Create_postgresql_extensions.sql
│   │   ├── V1.3__Create_categories_table.sql  # Categories table
│   │   └── V1.4__Create_products_table.sql    # Products table
│   ├── messages/                               # i18n messages
│   │   ├── messages.properties
│   │   ├── exception.properties
│   │   └── validation.properties
│   ├── templates/                              # Thymeleaf templates
│   │   └── product-list.html                  # Product listing UI
│   └── static/                                 # Static resources
│       └── css/style.css
│
├── build.gradle                                # Gradle dependencies
├── docker-compose.yml                          # PostgreSQL container setup
├── Dockerfile                                  # Application container
├── .env                                        # Environment variables
├── .env.example                                # Environment template
├── README.md                                   # This file
└── scripts/
    └── init-db.sql                             # Database initialization (optional)
```

## 🗄️ Database Schema

### Users Table
- User registration & authentication
- Password hashing (BCrypt)
- Role assignment

### Categories Table
- Product categories (Electronics, Clothing, etc.)
- Supports category hierarchies

### Products Table
- Product information (name, price, stock)
- Links to categories
- Stock tracking

### Audit Log Table
- Tracks all user actions
- Timestamp & user identification
- Action details logging

---

## 🚀 Quick Start

### Prerequisites
- Docker & Docker Compose installed
- Or: Java 21, PostgreSQL 16 (for local development)
- Gradle 9.2.1 or higher

### Step 1: Clone & Setup Environment
```bash
# Clone the repository
git clone <repository-url>
cd SpringBoot_CRUD

# Create .env file (if not exists)
cp .env.example .env
```

### Step 2: Start PostgreSQL Database
```bash
docker-compose up -d postgres
```

### Step 3: Build & Run Application

**Option A: With Hot Reload (Development)**
```bash
./gradlew bootRun
```
The application will auto-reload on file changes.

**Option B: Build & Run JAR**
```bash
./gradlew clean build -x test
java -jar build/libs/springboot-boilerplate-1.0.0.jar
```

### Step 4: Access the Application

**Web UI - Product List:**
```
http://localhost:8089/products
```

**API Documentation (Swagger):**
```
http://localhost:8089/swagger-ui.html
```

**Health Check:**
```bash
curl http://localhost:8089/health
```

---

## 📖 API Endpoints

### Authentication Endpoints
```bash
# Register new user
POST /register
Content-Type: application/json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "Test@1234",
  "firstName": "Test",
  "lastName": "User"
}

# Login
POST /login
Content-Type: application/json
{
  "username": "testuser",
  "password": "Test@1234"
}
Response: { "token": "eyJhbGc...", "type": "Bearer" }
```

### Product API (with JWT auth)
```bash
# Get all products
GET /api/products
Authorization: Bearer <JWT_TOKEN>

# Get product by ID
GET /api/products/{id}
Authorization: Bearer <JWT_TOKEN>

# Create product
POST /api/products
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
{
  "name": "iPhone 15",
  "price": 20000000,
  "stock": 50,
  "categoryId": 1
}

# Update product
PUT /api/products/{id}
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
{
  "name": "iPhone 15 Pro",
  "price": 25000000,
  "stock": 40,
  "categoryId": 1
}

# Delete product
DELETE /api/products/{id}
Authorization: Bearer <JWT_TOKEN>
```

### Category API (with JWT auth)
```bash
# Get all categories
GET /api/categories
Authorization: Bearer <JWT_TOKEN>

# Create category
POST /api/categories
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
{
  "name": "Electronics",
  "description": "Electronic devices"
}

# Update category
PUT /api/categories/{id}
Authorization: Bearer <JWT_TOKEN>

# Delete category
DELETE /api/categories/{id}
Authorization: Bearer <JWT_TOKEN>
```

### Health & Status
```bash
# Health check (no auth required)
GET /health

# Hello endpoint (protected)
GET /hello
Authorization: Bearer <JWT_TOKEN>
```

---

## 🔨 Build from Source

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

**3. Run Application:**
```bash
./gradlew bootRun
```

**4. Test Full Workflow:**
```bash
# 1. Register user
curl -X POST http://localhost:8089/register \
  -H "Content-Type: application/json" \
  -d '{
    "username":"admin",
    "email":"admin@example.com",
    "password":"Admin@1234",
    "firstName":"Admin",
    "lastName":"User"
  }'

# 2. Login & get token
TOKEN=$(curl -X POST http://localhost:8089/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"Admin@1234"}' \
  | jq -r '.token')

# 3. Create category
curl -X POST http://localhost:8089/api/categories \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Electronics","description":"Electronic devices"}'

# 4. Create product
curl -X POST http://localhost:8089/api/products \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "name":"iPhone 15",
    "price":20000000,
    "stock":50,
    "categoryId":1
  }'

# 5. View product list in browser
# Open: http://localhost:8089/products
```

---

## 🌐 Environment Configuration

Create `.env` file in project root:

```properties
# Application
SPRING_PROFILE=local
PORT=8089
TZ=GMT+7

# Database (PostgreSQL)
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=springboot_crud
POSTGRES_USER=haihoan
POSTGRES_PASSWORD=trinhhaihoan
DB_SCHEMA=public

# JWT Configuration
JWT_SECRETKEY=your-secret-key-change-in-production
JWT_ISSUER=springboot-boilerplate
JWT_EXPIRATIONMINUTE=60

# Swagger Configuration
SWAGGER_APPNAME=Inventory Management System
SWAGGER_APPDESCRIPTION=Product & Inventory Management API
SWAGGER_APPVERSION=1.0.0
SWAGGER_APPLICENSE=Apache 2.0
SWAGGER_APPLICENSE_URL=https://www.apache.org/licenses/LICENSE-2.0.html
SWAGGER_CONTACTNAME=Developer
SWAGGER_CONTACTMAIL=dev@example.com
SWAGGER_CONTACTURL=https://example.com
```

---

## 🐳 Docker Setup

### Start Services
```bash
# Start PostgreSQL only
docker-compose up -d postgres

# Start all services (app + postgres)
docker-compose up -d

# View logs
docker-compose logs -f postgres
docker-compose logs -f app
```

### Stop Services
```bash
docker-compose down
```

---

## 📱 Web UI Features

### Product List Page (`/products`)
- **View all products** in a beautiful table layout
- **Display product details**: ID, Name, Price, Category, Stock
- **Stock status**: Color-coded (green for available, red for out-of-stock)
- **Responsive design**: Mobile-friendly with Tailwind CSS
- **Icons**: Font Awesome icons for better UX
- **Empty state**: Shows message when no products available

### Features to Add (Upcoming)
- 🔄 Edit product functionality
- 🗑️ Delete product functionality
- ➕ Add new product form
- 🔍 Search & filter products
- 📄 Pagination support
- 🏷️ Category filter

---

## 🔐 Security Features

- **Password Hashing**: BCrypt algorithm with secure salting
- **JWT Token**: 60-minute expiration with refresh mechanism support
- **Role-Based Access Control**: ROLE_USER, ROLE_ADMIN authorization
- **Input Validation**: Spring Validation annotations on all DTOs
- **Global Exception Handling**: Consistent error responses
- **Audit Logging**: Complete user action tracking
- **CORS Configuration**: Secure cross-origin requests
- **HTTPS Ready**: SSL/TLS support in production

---

## 📊 Database Migrations

Flyway handles all schema migrations automatically:
- `V1.0__Create_users_table.sql` - User management with roles
- `V1.1__Create_audit_log_table.sql` - Audit trail for compliance
- `V1.2__Create_postgresql_extensions.sql` - PostgreSQL features (UUID, etc.)
- `V1.3__Create_categories_table.sql` - Product categories with hierarchy
- `V1.4__Create_products_table.sql` - Products inventory with stock tracking

---

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 4.0.0 | Web framework |
| Spring Security | 6.5 | Authentication & Authorization |
| Spring Data JPA | 3.4.0+ | Database ORM |
| PostgreSQL | 16 | Database |
| Flyway | Latest | Schema migrations |
| Thymeleaf | Latest | Server-side template engine |
| Tailwind CSS | Latest | Utility-first CSS framework |
| Font Awesome | 6.0.0 | Icon library |
| MapStruct | 1.6.3 | Type-safe DTO mapping |
| Lombok | Latest | Reduce boilerplate code |
| JWT | 4.5.0 | Token authentication |
| Swagger/OpenAPI | 2.8.13 | API documentation |
| Java | 21 LTS | Programming language |
| Gradle | 9.2.1 | Build tool |
| Docker | Latest | Containerization |

---

## 📝 Development Tips

### Hot Reload
Enable automatic restart on code changes:
```bash
./gradlew bootRun
# Modify any Java file and save - app auto-restarts
```

### View SQL Queries
Edit `application.properties`:
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
```

### Debug Mode
```bash
./gradlew bootRun --debug
```

### Database Connection Issues?
```bash
# Check PostgreSQL is running
docker-compose ps

# View PostgreSQL logs
docker-compose logs postgres

# Connect to PostgreSQL directly
psql -h localhost -U haihoan -d springboot_crud
```

### Check Application Health
```bash
curl -v http://localhost:8089/health
curl -v http://localhost:8089/actuator
```

---

## 📧 Support & Contact

- **Email**: dev@example.com
- **Issues**: Create an issue on GitHub
- **Documentation**: http://localhost:8089/swagger-ui.html

---

## 📄 License

Apache License 2.0 - See LICENSE file for details

---

## 📊 Project Statistics

- **Lines of Code**: 2000+
- **API Endpoints**: 15+
- **Database Tables**: 5+
- **Test Coverage**: In progress
- **Documentation**: Complete

---

**Version**: 1.0.0 | **Java**: 21 | **Spring Boot**: 4.0.0 | **Status**: ✅ Production Ready

**Quick Start**: 
```bash
docker-compose up -d postgres && ./gradlew bootRun && open http://localhost:8089/products
```

**Last Updated**: December 2025  
**Maintained By**: Development Team

