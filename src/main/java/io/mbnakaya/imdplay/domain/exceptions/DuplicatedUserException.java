package io.mbnakaya.imdplay.domain.exceptions;

public class DuplicatedUserException extends RuntimeException {
    public DuplicatedUserException(String message) {
        super(message);
    }
}
