package io.mbnakaya.imdplay.domain.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Invalid user and/or password.");
    }
}
