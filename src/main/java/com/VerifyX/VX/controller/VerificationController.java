package com.VerifyX.VX.controller;

import com.VerifyX.VX.dto.VerificationResponse;
import com.VerifyX.VX.service.HashingService;
import com.VerifyX.VX.service.VerificationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/verify")
public class VerificationController {
    private HashingService hashingService;

private final VerificationService verificationService;
public VerificationController(VerificationService verificationService,HashingService hashingService) {
        this.verificationService = verificationService;
        this.hashingService = hashingService;

}
@PostMapping("/{verificationId}")
public VerificationResponse verifyDocument(@PathVariable String verificationId,@RequestParam MultipartFile file) throws Exception {
    return verificationService.verifyDocument(verificationId,file);


}


}
