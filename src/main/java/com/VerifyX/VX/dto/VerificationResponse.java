package com.VerifyX.VX.dto;

import java.time.LocalDate;

public class VerificationResponse {

    private String verificationId;
    private String documentType;
    private String documentNumber;
    private String recipientName;
    private String organizationName;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String verificationStatus;



    private String message;

    public VerificationResponse(
            String verificationId,
            String documentType,
            String documentNumber,
            String recipientName,
            String organizationName,
            LocalDate issueDate,
            LocalDate expiryDate,
            String verificationStatus) {

        this.verificationId = verificationId;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.recipientName = recipientName;
        this.organizationName = organizationName;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.verificationStatus = verificationStatus;
    }

    public VerificationResponse(String verificationStatus, String message) {
        this.verificationStatus = verificationStatus;
        this.message = message;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }
    public String getMessage() {
        return message;
    }
}