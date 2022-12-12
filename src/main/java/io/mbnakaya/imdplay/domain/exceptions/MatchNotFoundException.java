package io.mbnakaya.imdplay.domain.exceptions;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException() {
        super("Error to locate match by informed ID.");
    }
}
