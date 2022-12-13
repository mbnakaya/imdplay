package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.Login;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository repository;

    public LoginServiceImpl(UserRepository bean) {
        this.repository = bean;
    }

    @Override
    public Login authenticate(Login login) {
        User user = repository.login(login.getUser().getUserName(), login.getUser().getPassword());
        return Login.builder()
                .user(user)
                .authToken(generateBasicAuth(user.getUserName(), user.getPassword()))
                .build();
    }

    private String generateBasicAuth(String userName, String password) {
        String encoding = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
        return "Basic " + encoding;
    }
}
