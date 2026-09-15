package com.VerifyX.VX.controller;

import com.VerifyX.VX.entity.Document;
import com.VerifyX.VX.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

private final DocumentService documentService;


    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public Document save(@RequestBody Document document) {
        return documentService.SaveDocument(document);
    }
    @GetMapping
    public List<Document> findAll() {
        return  documentService.getAllDocuments();
    }
}
