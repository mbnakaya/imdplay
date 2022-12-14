package io.mbnakaya.imdplay.domain.exceptions;

public class InvalidResultException extends RuntimeException {
    public InvalidResultException() {
        super("Invalid result.");
    }
}
