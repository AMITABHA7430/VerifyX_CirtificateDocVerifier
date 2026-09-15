package com.VerifyX.VX.service;

import com.VerifyX.VX.Repository.DocumentRepository;
import com.VerifyX.VX.entity.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;


    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }


    public Document SaveDocument(Document document) {
        String verificationId = "VX-" + UUID.randomUUID();

        document.setVerificationId(verificationId);

        return documentRepository.save(document);
    }


    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
