VerifyX — Document Verification & Authenticity Platform

A Java + Spring Boot backend designed to make digital document verification secure, simple, and traceable.

🚀 Overview

VerifyX is a backend-focused platform built to solve a real-world problem: digital certificates and documents can be copied, modified, or difficult to verify.

The system provides a secure workflow for organizations to issue documents and allows recipients or third parties to verify their authenticity and integrity.

🎯 Problem

Digital documents can be difficult to trust when shared electronically. A verifier may need to know:

Was the document issued by the organization?

Has it been modified?

Is it still valid?

Has it been revoked?

VerifyX is designed to provide a centralized workflow for these checks.

🔑 Key Features

JWT authentication and role-based access control

Document issuance and management

SHA-256 hash-based integrity verification

QR-code based verification

Document status and revocation

Audit logging for traceability

Validation and global exception handling

RESTful API design

Swagger / OpenAPI documentation

🛠️ Tech Stack

Java | Spring Boot | Spring Security | JWT | Spring Data JPA | Hibernate | PostgreSQL | REST APIs | SHA-256 | QR Code | Swagger/OpenAPI | Docker | Git/GitHub

🏗️ Backend Architecture

VerifyX follows a Controller → Service → Repository architecture.

Client / React
      ↓
   REST API
      ↓
 Controller
      ↓
   Service
      ↓
 Repository
      ↓
 PostgreSQL

Controller: Handles HTTP requests and API responses

Service: Contains business logic

Repository: Handles database operations with Spring Data JPA

Entity: Represents database models

Exception: Handles application errors centrally

Config: Contains security and application configuration

🔄 Core Workflow

Organization
     ↓
Issue Document
     ↓
Generate Verification ID
     ↓
Generate SHA-256 Hash
     ↓
Store Document Information
     ↓
Generate QR Code
     ↓
User Scans / Enters Verification ID
     ↓
Verify Document
     ↓
VALID / TAMPERED / REVOKED / EXPIRED

🔐 Document Integrity

VerifyX uses SHA-256 as a digital fingerprint for document integrity.

Document → SHA-256 → Stored Hash

During verification, the system can calculate the hash again and compare it with the trusted stored value. A matching hash indicates unchanged content; a different hash indicates that the content has changed.

Hashing verifies integrity against a trusted original record; hashing alone does not prove who originally issued a document.

🗄️ Database Design

Organization
   ├── User
   └── Document
          └── AuditLog

A document can store its verification ID, type, number, recipient, issue/expiry dates, status, SHA-256 hash, and issuing organization.

🌐 REST API

Example endpoints:

POST /api/auth/register
POST /api/auth/login

POST /api/documents
GET  /api/documents
GET  /api/documents/{id}
POST /api/documents/{id}/revoke
GET  /api/verify/{verificationId}

📂 Backend Structure

src/main/java/com/VerifyX/VX/
├── config/
├── controller/
├── entity/
├── exception/
├── repository/
├── service/
└── VxApplication.java


📚 What I Learned

This project gave me practical experience in:

Java backend development

Spring Boot and REST APIs

Spring Security and JWT

JPA/Hibernate and PostgreSQL

Layered backend architecture

SHA-256 document integrity verification

QR-based verification workflows

Validation and exception handling

Building software around a real-world problem

👨‍💻 Author

Amitabha Ghosh
Computer Science & Engineering Student

LinkedIn:[ https://linkedin.com/in/amitabha-ghosh](https://lnkd.in/p/dPUiJQfK)
