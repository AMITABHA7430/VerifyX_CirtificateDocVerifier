package com.VerifyX.VX.service;

import com.VerifyX.VX.Repository.DocumentRepository;
import com.VerifyX.VX.dto.VerificationResponse;
import com.VerifyX.VX.entity.Document;
import com.VerifyX.VX.entity.DocumentStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class VerificationService {

    private final DocumentRepository documentRepository;
    private final HashingService hashingService;

    public VerificationService(
            DocumentRepository documentRepository,
            HashingService hashingService) {

        this.documentRepository = documentRepository;
        this.hashingService = hashingService;
    }

    public VerificationResponse verifyDocument(
            String verificationId,
            MultipartFile file) throws Exception {

        Document document = documentRepository
                .findByVerificationId(verificationId)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));

        String submittedHash = hashingService.generateHash(file);

        String verificationStatus;
        String message;

        if (document.getStatus() == DocumentStatus.REVOKED) {

            verificationStatus = "REVOKED";
            message = "This document has been revoked.";

        } else if (document.getExpiryDate() != null
                && document.getExpiryDate().isBefore(LocalDate.now())) {

            verificationStatus = "EXPIRED";
            message = "This document has expired.";

        } else if (document.getDocumentHash().equals(submittedHash)) {

            verificationStatus = "VALID";
            message = "Document verified successfully.";

        } else {

            verificationStatus = "TAMPERED";
            message = "The submitted document does not match the original document.";
        }

        if (!verificationStatus.equals("VALID")) {

            return new VerificationResponse(
                    verificationStatus,
                    message
            );
        }

        return new VerificationResponse(
                document.getVerificationId(),
                document.getDocumentType(),
                document.getDocumentNumber(),
                document.getRecipientName(),
                document.getOrganization().getName(),
                document.getIssueDate(),
                document.getExpiryDate(),
                verificationStatus
        );
    }
}