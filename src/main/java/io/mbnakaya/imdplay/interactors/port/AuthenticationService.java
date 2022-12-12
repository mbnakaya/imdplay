package io.mbnakaya.imdplay.interactors.port;

public interface AuthenticationService {

    String getUserName(String authToken);
    Boolean authenticate(String authToken);
}
