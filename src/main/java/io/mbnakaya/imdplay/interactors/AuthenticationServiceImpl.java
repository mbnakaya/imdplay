package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String getUserName(String authToken) {
        return splitToUserName(authToken);
    }

    @Override
    public Boolean authenticate(String authToken) {
        User user = userRepository.login(
                splitToUserName(authToken),
                splitToPassword(authToken)
        );

        return user != null;
    }

    private String splitToUserName(String authToken) {
        String cleanedToken = authToken.replace("Basic ", "");
        String decodedToken = new String(Base64.getDecoder().decode(cleanedToken));
        return decodedToken.split(":")[0];
    }

    private String splitToPassword(String authToken) {
        String cleanedToken = authToken.replace("Basic ", "");
        String decodedToken = new String(Base64.getDecoder().decode(cleanedToken));
        return decodedToken.split(":")[1];
    }
}
