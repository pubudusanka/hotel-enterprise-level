# 🏨 Hotel Management Enterprise System

A comprehensive microservices-based hotel management system built with Spring Boot, featuring service discovery, API gateway, authentication, and full CRUD operations for hotels, branches, rooms, facilities, and addresses.

## 📋 Table of Contents

- [Architecture Overview](#architecture-overview)
- [Microservices](#microservices)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Usage Examples](#usage-examples)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## 🏗️ Architecture Overview

This system follows a microservices architecture with the following components:

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   API Gateway   │────│  Eureka Server   │────│   Auth Service  │
│   (Port: 9090)  │    │  (Port: 8761)    │    │  (Port: 9092)   │
└─────────────────┘    └──────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌────────────────────┐
                    │   Hotel API Service│
                    │    (Port: 9091)    │
                    └────────────────────┘
```

### Key Features:
- **Service Discovery**: Netflix Eureka for automatic service registration and discovery
- **API Gateway**: Spring Cloud Gateway for routing and load balancing
- **Authentication**: Keycloak integration with JWT tokens
- **Database**: MySQL with JPA/Hibernate
- **Email Service**: SendGrid integration for notifications
- **Cloud Storage**: AWS S3 for file uploads

## 🔧 Microservices

### 1. Hotel Management API Service (`hotel-management-service-api`)
**Port:** 9091
- Core business logic for hotel management
- CRUD operations for Hotels, Branches, Rooms, Facilities, and Addresses
- Pagination support for all list endpoints
- Search functionality

### 2. Authentication Service (`hotel-management-service-auth-service`)
**Port:** 9092
- User registration and login
- Email verification with OTP
- Password reset functionality
- JWT token generation
- Keycloak integration

### 3. API Gateway (`hotel-management-service-gateway`)
**Port:** 9090
- Routes requests to appropriate services
- OAuth2 resource server configuration
- Load balancing and security

### 4. Eureka Server (`hotel-management-service-eureka-server`)
**Port:** 8761
- Service discovery and registration
- Health monitoring of microservices

## 🛠️ Technology Stack

### Backend
- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Cloud 2025.0.0**
- **Spring Data JPA**
- **Spring Security OAuth2**
- **Netflix Eureka**
- **Spring Cloud Gateway**

### Database & Storage
- **MySQL 8.0**
- **AWS S3** (for file uploads)
- **Redis** (future caching)

### Security & Authentication
- **Keycloak** (Identity and Access Management)
- **JWT** (JSON Web Tokens)
- **OAuth2** (Authorization)

### Communication
- **SendGrid** (Email service)
- **REST APIs** (Inter-service communication)

### Development Tools
- **Maven** (Build tool)
- **Lombok** (Code generation)
- **Spring Boot DevTools** (Development)

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 21** or higher
- **Maven 3.6+**
- **MySQL 8.0**
- **Git**
- **Keycloak Server** (for authentication)
- **AWS Account** (for S3 storage)
- **SendGrid Account** (for email services)

### External Services Setup

1. **Keycloak Configuration:**
   - Download and install Keycloak
   - Create realm: `hotel-service`
   - Create client: `hotel-client`
   - Configure users and roles

2. **AWS S3 Bucket:**
   - Create S3 bucket: `hotel-auth-bucket`
   - Configure IAM user with S3 permissions
   - Update credentials in `application.properties`

3. **SendGrid:**
   - Create SendGrid account
   - Generate API key
   - Verify sender email

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/hotel-management-enterprise-level.git
cd hotel-management-enterprise-level/backend
```

### 2. Database Setup
```sql
-- Create databases
CREATE DATABASE hotel_service;
CREATE DATABASE hotel_auth_service;

-- Grant permissions (adjust username/password as needed)
GRANT ALL PRIVILEGES ON hotel_service.* TO 'root'@'localhost';
GRANT ALL PRIVILEGES ON hotel_auth_service.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure Application Properties

Update the following files with your credentials:

**hotel-management-service-api/src/main/resources/application.properties:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_service?createDatabaseIfNotExist=true
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

**hotel-management-service-auth-service/src/main/resources/application.properties:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_auth_service?createDatabaseIfNotExist=true
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

# Keycloak configuration
keycloak.config.name=your_keycloak_username
keycloak.config.secret=your_keycloak_client_secret
keycloak.config.password=your_keycloak_password

# SendGrid configuration
emailKey=your_sendgrid_api_key
fromEmail=your_verified_sender_email

# AWS configuration
accessKey=your_aws_access_key
secret=your_aws_secret_key
```

### 4. Start Services in Order

1. **Start Eureka Server:**
```bash
cd hotel-management-service-eureka-server
mvn spring-boot:run
```

2. **Start Auth Service:**
```bash
cd ../hotel-management-service-auth-service
mvn spring-boot:run
```

3. **Start API Service:**
```bash
cd ../hotel-management-service-api
mvn spring-boot:run
```

4. **Start Gateway:**
```bash
cd ../hotel-management-service-gateway
mvn spring-boot:run
```

### 5. Verify Installation

Check Eureka dashboard at: `http://localhost:8761`

## 📚 API Documentation

### Base URLs
- **Gateway:** `http://localhost:9090`
- **API Service:** `http://localhost:9091`
- **Auth Service:** `http://localhost:9092`

### Authentication Endpoints

#### User Registration
```http
POST /user-service/api/v1/visitors/signup
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}
```

#### User Login
```http
POST /user-service/api/v1/visitors/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

#### Email Verification
```http
POST /user-service/api/v1/visitors/verify-email
Content-Type: application/x-www-form-urlencoded

email=user@example.com&otp=123456
```

### Hotel Management Endpoints

#### Create Hotel
```http
POST /hotel-service/api/v1/hotels/user/create
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "name": "Grand Hotel",
  "description": "Luxury hotel in downtown",
  "contactNumber": "+1234567890",
  "email": "info@grandhotel.com"
}
```

#### Get All Hotels (Paginated)
```http
GET /hotel-service/api/v1/hotels/visitor/find-all?searchText=grand&page=0&size=10
```

#### Create Branch
```http
POST /hotel-service/api/v1/branch/user/create
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "name": "Downtown Branch",
  "description": "Main branch location",
  "contactNumber": "+1234567890",
  "hotelId": "hotel-uuid"
}
```

#### Create Room
```http
POST /hotel-service/api/v1/room/user/create
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "roomNumber": "101",
  "type": "DELUXE",
  "price": 150.00,
  "capacity": 2,
  "description": "Spacious deluxe room",
  "branchId": "branch-uuid"
}
```

#### Create Facility
```http
POST /hotel-service/api/v1/facility/user/create
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "name": "Swimming Pool",
  "description": "Outdoor swimming pool",
  "type": "RECREATION"
}
```

#### Create Address
```http
POST /hotel-service/api/v1/address/user/create
Authorization: Bearer <jwt_token>
Content-Type: application/json

{
  "street": "123 Main Street",
  "city": "New York",
  "state": "NY",
  "zipCode": "10001",
  "country": "USA",
  "branchId": "branch-uuid"
}
```

### Search and Filter Endpoints

#### Find Rooms by Branch
```http
GET /hotel-service/api/v1/room/visitor/find-by-branch-id?branchId=branch-uuid&page=0&size=10
```

#### Find Facilities by Name
```http
GET /hotel-service/api/v1/facility/visitor/find-rooms-by-facility-name?facilityName=pool&page=0&size=10
```

#### Find Addresses by City
```http
GET /hotel-service/api/v1/address/visitor/find-by-city-name?city=New%20York&page=0&size=10
```

## 🗄️ Database Schema

### Core Entities

#### Hotel
- `id` (UUID, Primary Key)
- `name` (String)
- `description` (Text)
- `contactNumber` (String)
- `email` (String)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

#### Branch
- `id` (UUID, Primary Key)
- `name` (String)
- `description` (Text)
- `contactNumber` (String)
- `hotelId` (UUID, Foreign Key)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

#### Room
- `id` (UUID, Primary Key)
- `roomNumber` (String)
- `type` (Enum: STANDARD, DELUXE, SUITE)
- `price` (Decimal)
- `capacity` (Integer)
- `description` (Text)
- `branchId` (UUID, Foreign Key)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

#### Facility
- `id` (UUID, Primary Key)
- `name` (String)
- `description` (Text)
- `type` (Enum: RECREATION, SERVICE, AMENITY)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

#### Address
- `id` (UUID, Primary Key)
- `street` (String)
- `city` (String)
- `state` (String)
- `zipCode` (String)
- `country` (String)
- `branchId` (UUID, Foreign Key)
- `createdAt` (Timestamp)
- `updatedAt` (Timestamp)

## 🔐 Security

### Authentication Flow
1. User registers via `/visitors/signup`
2. System sends verification email with OTP
3. User verifies email via `/visitors/verify-email`
4. User can now login via `/visitors/login`
5. JWT token is returned for subsequent API calls

### Authorization Levels
- **VISITOR**: Read-only access to public endpoints
- **USER**: Can create resources
- **ADMIN**: Can update resources
- **HOST**: Can delete resources

### JWT Token Usage
Include the Bearer token in Authorization header:
```
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## 💡 Usage Examples

### Complete Hotel Setup Flow

1. **Register and Login:**
```bash
# Register user
curl -X POST http://localhost:9090/user-service/api/v1/visitors/signup \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@hotel.com","password":"password123","firstName":"Admin","lastName":"User"}'

# Verify email (get OTP from email)
curl -X POST http://localhost:9090/user-service/api/v1/visitors/verify-email \
  -d "email=admin@hotel.com&otp=123456"

# Login
curl -X POST http://localhost:9090/user-service/api/v1/visitors/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@hotel.com","password":"password123"}'
```

2. **Create Hotel:**
```bash
curl -X POST http://localhost:9090/hotel-service/api/v1/hotels/user/create \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Grand Plaza Hotel","description":"Luxury hotel","contactNumber":"+1234567890","email":"info@grandplaza.com"}'
```

3. **Create Branch:**
```bash
curl -X POST http://localhost:9090/hotel-service/api/v1/branch/user/create \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Main Branch","description":"Downtown location","contactNumber":"+1234567890","hotelId":"hotel-uuid"}'
```

4. **Add Rooms and Facilities:**
```bash
# Create facility
curl -X POST http://localhost:9090/hotel-service/api/v1/facility/user/create \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Fitness Center","description":"24/7 gym","type":"RECREATION"}'

# Create room
curl -X POST http://localhost:9090/hotel-service/api/v1/room/user/create \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"roomNumber":"101","type":"DELUXE","price":200.00,"capacity":2,"description":"City view room","branchId":"branch-uuid"}'
```

## 🧪 Testing

### Unit Tests
```bash
# Run tests for all services
mvn test

# Run tests for specific service
cd hotel-management-service-api
mvn test
```

### Integration Tests
```bash
# Test with all services running
mvn verify
```

### Manual Testing with Postman
Import the provided Postman collection: `docs/Hotel_Management_API.postman_collection.json`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Commit your changes: `git commit -m 'Add amazing feature'`
4. Push to the branch: `git push origin feature/amazing-feature`
5. Open a Pull Request

### Code Standards
- Follow Spring Boot best practices
- Use meaningful variable and method names
- Add comprehensive JavaDoc comments
- Write unit tests for new features
- Ensure all tests pass before submitting PR

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:
- **GitHub**: [pubudusanka](https://github.com/pubudusanka)
- **Email**: pubudusanka79@gmail.com

## 🙏 Acknowledgments

- Spring Boot and Spring Cloud communities
- Netflix OSS for Eureka
- Keycloak for identity management
- SendGrid for email services
- AWS for cloud storage solutions

---
