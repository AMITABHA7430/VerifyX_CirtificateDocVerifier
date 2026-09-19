package com.VerifyX.VX.service;

import com.VerifyX.VX.Repository.DocumentRepository;
import com.VerifyX.VX.entity.Document;
import com.VerifyX.VX.entity.DocumentStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final HashingService hashingService;

    public DocumentService(
            DocumentRepository documentRepository,
            HashingService hashingService) {

        this.documentRepository = documentRepository;
        this.hashingService = hashingService;
    }

    public Document SaveDocument(Document document, MultipartFile file)
            throws Exception {

        String verificationId = "VX-" + UUID.randomUUID();

        document.setVerificationId(verificationId);

        String hash = hashingService.generateHash(file);

        document.setDocumentHash(hash);

        document.setStatus(DocumentStatus.ACTIVE);

        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}