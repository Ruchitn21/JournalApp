# 📖 JournalApp - Personal Digital Journal

[![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-brightgreen?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-6.0+-green?style=flat-square&logo=mongodb)](https://www.mongodb.com/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue?style=flat-square&logo=apache-maven)](https://maven.apache.org/)

## 🚀 Overview

JournalApp is a modern, full-featured personal digital journal application built with Spring Boot and MongoDB. It provides users with a seamless experience for creating, managing, and organizing their personal thoughts, experiences, and daily reflections in a secure and intuitive platform.

## ✨ Key Features

- **🔐 User Authentication & Authorization**: Secure JWT-based authentication system
- **📝 Journal Entry Management**: Create, read, update, and delete journal entries
- **👤 User Profile Management**: Comprehensive user account management
- **🔍 Advanced Search**: Find entries by date, content, or tags
- **🏷️ Categorization**: Organize entries with custom tags and categories
- **📱 RESTful API Design**: Clean and intuitive API endpoints
- **🛡️ Data Security**: Encrypted data storage and secure API endpoints
- **⚡ High Performance**: Optimized database queries and caching

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot 3.0+**: Modern Java framework for building production-ready applications
- **Spring Web**: For building RESTful web services
- **Spring Data MongoDB**: Simplified data access layer for MongoDB
- **Spring Security**: Comprehensive security framework for authentication and authorization
- **Spring Boot Actuator**: Production-ready monitoring and management features

### Database
- **MongoDB**: NoSQL document database for flexible data modeling
- **Spring Data MongoDB**: Object-Document Mapping (ODM) for seamless database operations

### Security & Authentication
- **JWT (JSON Web Tokens)**: Stateless authentication mechanism
- **BCrypt**: Password hashing for secure credential storage
- **Spring Security**: Role-based access control and method-level security

### Development & Build Tools
- **Java 17**: Latest LTS version with modern language features
- **Maven**: Dependency management and build automation
- **Lombok**: Reduces boilerplate code with annotations

### Additional Libraries
- **Jackson**: JSON processing for API serialization/deserialization
- **Validation API**: Bean validation for input data validation
- **Swagger/OpenAPI**: API documentation and testing interface

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- MongoDB 6.0 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/Ruchitn21/JournalApp.git
cd JournalApp
```

### 2. Configure MongoDB
```properties
# In application.properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=journaldb
```

### 3. Build and Run
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Authentication APIs

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123"
}
```

**Response:**
```json
{
    "message": "User registered successfully",
    "userId": "64a7b8c9d1e2f3a4b5c6d7e8"
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "john_doe",
    "password": "securePassword123"
}
```

**Response:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "type": "Bearer",
    "username": "john_doe",
    "email": "john@example.com"
}
```

### Journal Entry APIs

#### Create Journal Entry
```http
POST /api/journal-entries
Authorization: Bearer {token}
Content-Type: application/json

{
    "title": "My Amazing Day",
    "content": "Today was absolutely wonderful. I learned so much about Spring Boot and MongoDB integration...",
    "tags": ["learning", "technology", "spring-boot"],
    "mood": "happy"
}
```

**Response:**
```json
{
    "id": "64a7b8c9d1e2f3a4b5c6d7e9",
    "title": "My Amazing Day",
    "content": "Today was absolutely wonderful...",
    "tags": ["learning", "technology", "spring-boot"],
    "mood": "happy",
    "createdAt": "2024-07-15T10:30:00Z",
    "updatedAt": "2024-07-15T10:30:00Z"
}
```

#### Get All Journal Entries
```http
GET /api/journal-entries
Authorization: Bearer {token}
```

**Response:**
```json
[
    {
        "id": "64a7b8c9d1e2f3a4b5c6d7e9",
        "title": "My Amazing Day",
        "content": "Today was absolutely wonderful...",
        "tags": ["learning", "technology"],
        "mood": "happy",
        "createdAt": "2024-07-15T10:30:00Z"
    }
]
```

#### Get Journal Entry by ID
```http
GET /api/journal-entries/{id}
Authorization: Bearer {token}
```

#### Update Journal Entry
```http
PUT /api/journal-entries/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
    "title": "Updated Title",
    "content": "Updated content with new insights...",
    "tags": ["updated", "reflection"],
    "mood": "contemplative"
}
```

#### Delete Journal Entry
```http
DELETE /api/journal-entries/{id}
Authorization: Bearer {token}
```

### User Management APIs

#### Get User Profile
```http
GET /api/users/profile
Authorization: Bearer {token}
```

**Response:**
```json
{
    "id": "64a7b8c9d1e2f3a4b5c6d7e8",
    "username": "john_doe",
    "email": "john@example.com",
    "createdAt": "2024-07-10T08:15:00Z",
    "totalEntries": 15,
    "lastLogin": "2024-07-15T09:45:00Z"
}
```

#### Update User Profile
```http
PUT /api/users/profile
Authorization: Bearer {token}
Content-Type: application/json

{
    "email": "newemail@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```

#### Change Password
```http
PUT /api/users/change-password
Authorization: Bearer {token}
Content-Type: application/json

{
    "currentPassword": "oldPassword123",
    "newPassword": "newSecurePassword456"
}
```

## 📊 Database Schema

### User Collection
```javascript
{
    "_id": ObjectId,
    "username": String (unique),
    "email": String (unique),
    "password": String (hashed),
    "firstName": String,
    "lastName": String,
    "roles": [String],
    "createdAt": Date,
    "updatedAt": Date,
    "lastLogin": Date
}
```

### Journal Entry Collection
```javascript
{
    "_id": ObjectId,
    "userId": ObjectId (reference to User),
    "title": String,
    "content": String,
    "tags": [String],
    "mood": String,
    "isPrivate": Boolean,
    "createdAt": Date,
    "updatedAt": Date
}
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── journalapp/
│   │           ├── JournalAppApplication.java
│   │           ├── config/
│   │           │   ├── SecurityConfig.java
│   │           │   ├── MongoConfig.java
│   │           │   └── JwtConfig.java
│   │           ├── controller/
│   │           │   ├── AuthController.java
│   │           │   ├── JournalEntryController.java
│   │           │   └── UserController.java
│   │           ├── entity/
│   │           │   ├── User.java
│   │           │   └── JournalEntry.java
│   │           ├── repository/
│   │           │   ├── UserRepository.java
│   │           │   └── JournalEntryRepository.java
│   │           ├── service/
│   │           │   ├── UserService.java
│   │           │   ├── JournalEntryService.java
│   │           │   └── AuthService.java
│   │           ├── dto/
│   │           │   ├── UserDto.java
│   │           │   ├── JournalEntryDto.java
│   │           │   └── AuthRequestDto.java
│   │           ├── security/
│   │           │   ├── JwtAuthenticationFilter.java
│   │           │   ├── JwtTokenProvider.java
│   │           │   └── UserDetailsServiceImpl.java
│   │           └── exception/
│   │               ├── GlobalExceptionHandler.java
│   │               └── CustomExceptions.java
│   └── resources/
│       ├── application.properties
│       └── application-prod.properties
└── test/
    └── java/
        └── com/
            └── journalapp/
                ├── controller/
                ├── service/
                └── repository/
```

## 🔧 Configuration

### Application Properties
```properties
# Server Configuration
server.port=8080

# MongoDB Configuration
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=journaldb

# JWT Configuration
jwt.secret=mySecretKey
jwt.expiration=86400000

# Logging Configuration
logging.level.com.journalapp=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

# Actuator Configuration
management.endpoints.web.exposure.include=health,info,metrics
```

## 🧪 Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn integration-test
```

### API Testing with Postman
Import the provided Postman collection for comprehensive API testing:
- Authentication flows
- CRUD operations for journal entries
- User management operations
- Error handling scenarios

## 📈 Performance & Monitoring

- **Spring Boot Actuator**: Health checks, metrics, and application insights
- **Database Indexing**: Optimized queries with proper MongoDB indexing
- **Caching**: Strategic caching for frequently accessed data
- **Logging**: Comprehensive logging with configurable levels

## 🔒 Security Features

- **JWT Authentication**: Stateless token-based authentication
- **Password Encryption**: BCrypt hashing for secure password storage
- **Input Validation**: Comprehensive input validation and sanitization
- **CORS Configuration**: Cross-origin resource sharing setup
- **Rate Limiting**: API rate limiting to prevent abuse

## 🚀 Deployment

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/journal-app-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables
```bash
export MONGODB_URI=mongodb://localhost:27017/journaldb
export JWT_SECRET=your-secret-key
export SERVER_PORT=8080
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Ruchit Neema**
- GitHub: [@Ruchitn21](https://github.com/Ruchitn21)
- LinkedIn: [Connect with me](https://linkedin.com/in/your-profile)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MongoDB team for the powerful NoSQL database
- The open-source community for continuous inspiration

---

⭐ **If you find this project helpful, please give it a star!** ⭐
