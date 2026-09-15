# VerifyX — Document Verification & Authenticity Platform

VerifyX is a backend-focused document verification platform designed to help organizations issue digitally verifiable documents and allow anyone to check their authenticity and integrity.

The system uses **Java, Spring Boot, PostgreSQL, and cryptographic hashing** to create a secure verification workflow for digital documents.

> 🚧 **Project Status:** In Development

---

## 📌 Problem Statement

Digital certificates and documents can be difficult to verify, especially when they are shared electronically.

A verifier may need to determine:

* Whether the document was actually issued by the organization
* Whether the document has been revoked
* Whether the document has expired
* Whether the document has been modified after issuance

VerifyX aims to provide a centralized and reliable way to answer these questions.

---

## 💡 How VerifyX Works

The basic workflow is:

```text
Organization
     │
     ▼
Issue Document
     │
     ▼
VerifyX generates a unique Verification ID
     │
     ▼
VerifyX generates a SHA-256 hash
     │
     ▼
Document information is stored
     │
     ▼
Recipient receives the document + Verification ID
     │
     ▼
Verifier submits Verification ID + document
     │
     ▼
VerifyX verifies the document
     │
     ▼
VALID / TAMPERED / REVOKED / EXPIRED
```

The goal is to make document verification simple while maintaining document integrity.

---

## ✨ Planned Features

### 🔐 Authentication & Authorization

* Organization registration and login
* JWT-based authentication
* Role-based access control
* Secure password storage

### 📄 Document Management

* Issue digital documents
* Store document metadata
* Generate unique Verification IDs
* Track document status
* Revoke documents

### 🔎 Document Verification

* Verify documents using a Verification ID
* Calculate and compare SHA-256 hashes
* Detect document tampering
* Check document expiration
* Check whether a document has been revoked

### 📱 QR Code Verification

* Generate QR codes linked to verification records
* Scan QR codes to access the verification process

### 📝 Audit Logging

* Track important document actions
* Maintain verification and revocation history

### 🛡️ Backend Engineering

* Input validation
* Global exception handling
* RESTful APIs
* Database relationships
* API documentation using Swagger/OpenAPI
* Automated testing

### 🐳 Deployment & DevOps

* Docker support
* Git/GitHub version control
* Production-ready configuration

---

## 🏗️ Technology Stack

| Technology            | Purpose                                    |
| --------------------- | ------------------------------------------ |
| **Java**              | Backend programming                        |
| **Spring Boot**       | Backend application framework              |
| **Spring Security**   | Authentication and authorization           |
| **JWT**               | Stateless authentication                   |
| **Spring Data JPA**   | Database access                            |
| **Hibernate**         | ORM                                        |
| **PostgreSQL**        | Relational database                        |
| **REST API**          | Communication between frontend and backend |
| **SHA-256**           | Document integrity verification            |
| **QR Code**           | Quick document verification                |
| **Swagger / OpenAPI** | API documentation                          |
| **Docker**            | Containerization                           |
| **Git & GitHub**      | Version control                            |

---

## 🧩 Project Architecture

VerifyX follows a **modular monolithic architecture**.

```text
                    ┌─────────────────┐
                    │    Frontend     │
                    │     React       │
                    └────────┬────────┘
                             │
                         REST API
                             │
                             ▼
              ┌──────────────────────────┐
              │      Spring Boot         │
              │        Backend           │
              │                          │
              │  Controller              │
              │      ↓                   │
              │  Service                 │
              │      ↓                   │
              │  Repository              │
              │      ↓                   │
              │  Entity                  │
              └────────────┬─────────────┘
                           │
                           ▼
                  ┌─────────────────┐
                  │   PostgreSQL    │
                  └─────────────────┘
```

The backend is divided into logical layers so that responsibilities remain separated and the application is easier to maintain and test.

---

## 📂 Backend Package Structure

```text
src/
└── main/
    └── java/
        └── com/VerifyX/VX/
            │
            ├── config/
            │
            ├── controller/
            │
            ├── entity/
            │
            ├── exception/
            │
            ├── repository/
            │
            ├── service/
            │
            └── VxApplication.java
```

### Controller

Handles incoming HTTP requests and returns responses.

### Service

Contains the application's business logic.

### Repository

Communicates with the database using Spring Data JPA.

### Entity

Represents database tables as Java classes.

### Exception

Handles application errors and provides meaningful API responses.

### Config

Contains application configuration such as security configuration.

---

## 🗄️ Database Design

The planned database contains the following major entities:

```text
Organization
     │
     ├──────────< User
     │
     └──────────< Document
                     │
                     └──────────< AuditLog
```

### Organization

Stores information about organizations that issue documents.

### User

Stores users belonging to organizations and their roles.

### Document

Stores document information such as:

* Verification ID
* Document type
* Document number
* Recipient
* Issue date
* Expiry date
* Status
* SHA-256 hash
* Issuing organization

### Audit Log

Records important actions performed on documents.

---

## 🔐 Document Integrity

VerifyX uses **SHA-256** to generate a digital fingerprint of a document.

For example:

```text
Document
   ↓
SHA-256
   ↓
9cc35973e38c6a51d1573eb3f1190943094b1f5a0e73521b26dcfcb7788e2270
```

When the document needs to be verified, VerifyX can calculate its hash again and compare it with the original stored hash.

```text
Original Hash
      │
      │
      ▼
   Compare
      ▲
      │
Submitted Document → SHA-256
```

If the hashes match, the document content has remained unchanged.

If they differ, the submitted document has been modified.

> **Note:** A hash verifies integrity against a trusted original record; hashing alone does not independently prove who originally issued a document.

---

## 🌐 REST API

The backend exposes RESTful APIs for interacting with VerifyX.

### Organization

```http
POST /api/organizations
GET  /api/organizations
GET  /api/organizations/{id}
```

### Documents

```http
POST /api/documents
GET  /api/documents
```

### Planned APIs

```http
POST /api/auth/register
POST /api/auth/login

GET  /api/documents/{id}
POST /api/documents/{id}/revoke

GET  /api/verify/{verificationId}
```

The API surface will expand as the project progresses.

---

## 🧪 Testing

Testing will be added throughout development to verify:

* REST API behavior
* Business logic
* Validation
* Exception handling
* Database operations
* Authentication and authorization
* Document verification logic

---

## 🚀 Getting Started

### Prerequisites

Make sure you have:

* Java 26+
* Maven
* PostgreSQL
* Git

### Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/VerifyX.git
cd VerifyX
```

### Configure PostgreSQL

Create a PostgreSQL database:

```sql
CREATE DATABASE verifyx;
```

Configure the database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/verifyx
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> For production, database credentials should be provided through environment variables or a secure secret-management system rather than committed to Git.

### Run the Application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or run the main Spring Boot class:

```text
VxApplication.java
```

The backend will start on:

```text
http://localhost:8080
```

---

## 📈 Development Roadmap

* [x] Spring Boot project setup
* [x] PostgreSQL database connection
* [x] Organization entity
* [x] Organization CRUD APIs
* [x] Validation and exception handling
* [x] Document entity
* [x] Organization–Document relationship
* [x] Document CRUD foundation
* [x] Automatic Verification ID generation
* [ ] SHA-256 document hashing
* [ ] Document verification API
* [ ] Tamper detection
* [ ] Document revocation
* [ ] Document expiration handling
* [ ] User authentication
* [ ] JWT security
* [ ] Role-based authorization
* [ ] DTO-based API design
* [ ] QR code generation
* [ ] Audit logging
* [ ] Swagger/OpenAPI documentation
* [ ] Automated testing
* [ ] React frontend
* [ ] Docker configuration
* [ ] Deployment

---

## 🎯 Project Goals

The main goals of VerifyX are to:

1. Build a practical real-world backend application.
2. Understand Spring Boot and REST API development.
3. Implement secure authentication and authorization.
4. Work with PostgreSQL and Hibernate.
5. Understand document integrity using cryptographic hashing.
6. Design a maintainable backend architecture.
7. Build a project that demonstrates practical backend engineering skills.

---

## 🔮 Future Improvements

Potential future improvements include:

* Digital signatures using asymmetric cryptography
* Advanced document storage
* Rate limiting
* Caching where genuinely useful
* Advanced audit trails
* Organization-level dashboards
* Cloud deployment
* Monitoring and observability

These features will be considered based on actual project requirements rather than being added only for technology-stack complexity.

---

## 👨‍💻 Author

**Amitabha Ghosh**

Computer Science & Engineering Student
Interested in **Java Backend Development, Software Engineering, and Problem Solving**.

---

## ⭐ Project Status

**VerifyX is actively being developed as a learning and portfolio project focused on real-world backend engineering.**

The project is being developed incrementally, with emphasis on understanding the underlying concepts, architecture, security, database design, and implementation rather than simply assembling a collection of technologies.
