package com.VerifyX.VX.service;

import com.VerifyX.VX.Repository.DocumentRepository;
import com.VerifyX.VX.Repository.UserRepository;
import com.VerifyX.VX.entity.Document;
import com.VerifyX.VX.entity.DocumentStatus;
import com.VerifyX.VX.entity.User;
import com.VerifyX.VX.exception.DocumentNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final HashingService hashingService;
    private final UserRepository userRepository;

    public DocumentService(
            DocumentRepository documentRepository,
            HashingService hashingService,
            UserRepository userRepository) {

        this.documentRepository = documentRepository;
        this.hashingService = hashingService;
        this.userRepository = userRepository;
    }

    public Document revokeDocument(Long id, String email) {


        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        Document document = documentRepository
                .findById(id)
                .orElseThrow(() ->
                        new DocumentNotFoundException(id));


        Long userOrganizationId =
                user.getOrganization().getId();


        Long documentOrganizationId =
                document.getOrganization().getId();


        if (!userOrganizationId.equals(documentOrganizationId)) {
            throw new RuntimeException(
                    "You are not allowed to modify this document");
        }


        document.setStatus(DocumentStatus.REVOKED);

        return documentRepository.save(document);
    }

    public Document SaveDocument(
            Document document,
            MultipartFile file) throws Exception {

        String verificationId =
                "VX-" + UUID.randomUUID();

        document.setVerificationId(verificationId);


        String hash =
                hashingService.generateHash(file);

        document.setDocumentHash(hash);


        document.setStatus(DocumentStatus.ACTIVE);

        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}