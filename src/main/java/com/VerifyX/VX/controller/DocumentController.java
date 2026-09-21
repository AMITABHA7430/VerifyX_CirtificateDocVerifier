package com.VerifyX.VX.controller;

import com.VerifyX.VX.entity.Document;
import com.VerifyX.VX.entity.User;
import com.VerifyX.VX.service.DocumentService;
import com.VerifyX.VX.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    private final UserService userService;

    public DocumentController(
            DocumentService documentService,
            UserService userService) {

        this.documentService = documentService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ORG_ADMIN', 'ISSUER')")
    @PostMapping("/upload")
    public Document upload(
            @RequestParam String documentType,
            @RequestParam String documentNumber,
            @RequestParam String recipientName,
            @RequestParam String issueDate,
            @RequestParam String expiryDate,
            @RequestParam MultipartFile file,
            Authentication authentication) throws Exception {


        String email = authentication.getName();


        User user = userService.getUserByEmail(email);

        Document document = new Document();

        document.setDocumentType(documentType);
        document.setDocumentNumber(documentNumber);
        document.setRecipientName(recipientName);

        document.setIssueDate(
                LocalDate.parse(issueDate)
        );

        document.setExpiryDate(
                LocalDate.parse(expiryDate)
        );


        document.setOrganization(
                user.getOrganization()
        );

        return documentService.SaveDocument(document, file);
    }

    @PreAuthorize("hasAnyRole('ORG_ADMIN', 'ISSUER')")
    @GetMapping
    public List<Document> findAll() {
        return documentService.getAllDocuments();
    }

    @PreAuthorize("hasAnyRole('ORG_ADMIN', 'ISSUER')")
    @PatchMapping("/{id}/revoke")
    public Document revokeDocument(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();

        return documentService.revokeDocument(id, email);
    }
}