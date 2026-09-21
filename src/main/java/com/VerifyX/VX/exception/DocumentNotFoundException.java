package com.VerifyX.VX.exception;

public class DocumentNotFoundException extends RuntimeException{


    public  DocumentNotFoundException(String message) {
        super("Document not found with verification ID: "+message);
    }
    public  DocumentNotFoundException(long id) {
        super("Document not found with verification ID: "+id);
    }
}
