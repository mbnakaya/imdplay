package io.mbnakaya.imdplay.domain.exceptions;

public class MatchAlreadyFinishedException extends RuntimeException {

    public MatchAlreadyFinishedException() {
        super("This match is already finished.");
    }
}
