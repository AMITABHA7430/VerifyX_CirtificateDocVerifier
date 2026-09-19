package com.VerifyX.VX.Repository;

import com.VerifyX.VX.entity.Document;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findByVerificationId(String verificationId);



}
