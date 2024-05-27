package org.example.storage;

public class StorageRuntimeException extends RuntimeException{
    public StorageRuntimeException(String errorMessage) {
        super(errorMessage);
    }
}