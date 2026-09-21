VerifyX — Document Verification & Authenticity Platform

A Java + Spring Boot backend built to make digital document verification secure, simple, and traceable.

GitHub • Features • Architecture • API • Security • Setup

1. Overview

VerifyX is a backend-focused platform for issuing and verifying digital certificates and documents.

It addresses a real-world problem: digital documents can be copied, modified, or difficult to verify. VerifyX provides a centralized workflow to check document integrity, status, and verification details.

2. Problem Statement

When a digital certificate is shared, a verifier may need to know:

Is it genuine?

Has it been modified?

Is it still valid?

Has it been revoked?

VerifyX is designed to answer these questions through a secure verification workflow.

3. How It Works

Organization
     ↓
Issue Document
     ↓
Generate Verification ID
     ↓
Generate SHA-256 Hash
     ↓
Store Verification Data
     ↓
Generate QR Code
     ↓
User Scans / Enters ID
     ↓
Verify Document
     ↓
VALID / TAMPERED / REVOKED / EXPIRED

4. Key Features

Feature

Description

🔐 Authentication

JWT-based authentication with Spring Security

👥 Authorization

Role-Based Access Control (RBAC)

📄 Document Management

Issue, store, verify, and revoke documents

🔑 Integrity Check

SHA-256 based document hash verification

📱 QR Verification

Quick access to document verification

📝 Audit Logging

Track important document actions

✅ Validation

Request validation and global exception handling

🌐 REST APIs

Backend APIs for application operations

📖 API Docs

Swagger / OpenAPI documentation

5. Technology Stack

Backend: Java, Spring Boot, Spring MVC, Spring Security, Spring AI

Persistence: Spring Data JPA, Hibernate

Database: PostgreSQL

Security: JWT, SHA-256

API & Development: REST APIs, Swagger/OpenAPI, Postman

Tools: Maven, Git, GitHub, Docker, IntelliJ IDEA, Eclipse

6. Backend Architecture

VerifyX follows a layered Controller → Service → Repository architecture.

             Client / React
                   │
                   ▼
               REST API
                   │
                   ▼
             ┌──────────┐
             │Controller│
             └────┬─────┘
                  ↓
             ┌──────────┐
             │ Service  │
             └────┬─────┘
                  ↓
             ┌──────────┐
             │Repository│
             └────┬─────┘
                  ↓
             ┌──────────┐
             │PostgreSQL│
             └──────────┘

Layer Responsibilities

Controller: Handles HTTP requests and API responses

Service: Contains business logic

Repository: Handles database operations using JPA

Entity: Represents persistent data models

Exception: Centralized error handling

Config: Security and application configuration

7. Security & Document Integrity

JWT + Spring Security

Authentication and authorization are handled through Spring Security and JWT, with role-based access to protected resources.

SHA-256 Verification

Each document can be represented by a SHA-256 hash.

Document
   ↓
SHA-256
   ↓
Stored Hash

During verification, the system can calculate the hash again and compare it with the trusted stored hash.

Stored Hash
     ↓
   Compare
     ↑
Document → SHA-256

A matching hash indicates unchanged content; a different hash indicates that the checked content has changed.

Hashing verifies integrity against a trusted original record; hashing alone does not prove who originally issued a document.

8. Database Design

Organization
   ├── User
   └── Document
          └── AuditLog

Document Data

Verification ID

Document type and number

Recipient

Issue and expiry dates

Status

SHA-256 hash

Issuing organization

9. REST API

Example endpoints:

POST /api/auth/register
POST /api/auth/login

POST /api/documents
GET  /api/documents
GET  /api/documents/{id}
POST /api/documents/{id}/revoke

GET  /api/verify/{verificationId}

10. Backend Project Structure

src/main/java/com/VerifyX/VX/
├── config/
├── controller/
├── entity/
├── exception/
├── repository/
├── service/
└── VxApplication.java

11. Getting Started

Prerequisites

Java

Maven

PostgreSQL

Git

Clone

git clone <YOUR-REPOSITORY-URL>
cd VerifyX

Configure Database

Update your PostgreSQL configuration in:

src/main/resources/application.properties

Run

./mvnw spring-boot:run

Application:

http://localhost:8080

12. Project Links



13. Key Learning

Through VerifyX, I gained hands-on experience in:

Java backend development with Spring Boot

REST API design and layered architecture

Spring Security and JWT authentication

JPA/Hibernate with PostgreSQL

Document integrity using SHA-256

QR-based verification workflows

Validation and exception handling

Building software around a real-world problem

14. Author

Amitabha Ghosh

Computer Science & Engineering Student | Java Backend Development

LinkedIn · GitHub

⭐ If you find the project interesting, consider giving it a star!
