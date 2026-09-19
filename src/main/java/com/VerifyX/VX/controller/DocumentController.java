package com.VerifyX.VX.controller;

import com.VerifyX.VX.entity.Document;
import com.VerifyX.VX.entity.Organization;
import com.VerifyX.VX.service.DocumentService;

import com.VerifyX.VX.service.OrganizationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    private final OrganizationService organizationService;

    public DocumentController(
            DocumentService documentService,
            OrganizationService organizationService) {

        this.documentService = documentService;
        this.organizationService = organizationService;
    }



    @PostMapping("/upload")
    public Document upload(
            @RequestParam Long organizationId,
            @RequestParam String documentType,
            @RequestParam String documentNumber,
            @RequestParam String recipientName,
            @RequestParam String issueDate,
            @RequestParam String expiryDate,
            @RequestParam MultipartFile file) throws Exception {

        Document document = new Document();

        document.setDocumentType(documentType);
        document.setDocumentNumber(documentNumber);
        document.setRecipientName(recipientName);
        document.setIssueDate(java.time.LocalDate.parse(issueDate));
        document.setExpiryDate(java.time.LocalDate.parse(expiryDate));
        Organization organization =
                organizationService.getOrganizationById(organizationId);

        document.setOrganization(organization);

        return documentService.SaveDocument(document, file);
    }

    @GetMapping
    public List<Document> findAll() {
        return documentService.getAllDocuments();
    }
}
