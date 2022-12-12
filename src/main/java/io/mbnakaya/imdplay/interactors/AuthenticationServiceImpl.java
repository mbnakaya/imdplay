package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public String getUserName(String authToken) {
        String cleanedToken = authToken.replace("Basic ", "");
        String decodedToken = new String(Base64.getDecoder().decode(cleanedToken));
        return decodedToken.split(":")[0];
    }
}
