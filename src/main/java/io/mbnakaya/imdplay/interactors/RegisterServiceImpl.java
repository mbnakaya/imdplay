package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.AuthenticationService;
import io.mbnakaya.imdplay.interactors.port.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository repository;
    private final AuthenticationService authenticationService;

    public RegisterServiceImpl(UserRepository repositoryBean, AuthenticationService authenticationServiceBean) {
        this.repository = repositoryBean;
        this.authenticationService = authenticationServiceBean;
    }

    @Override
    public User registry(User user) {
        User savedUser = repository.save(user);
        return savedUser;
    }

    @Override
    public User getUser(String authToken) {
        String userName = authenticationService.getUserName(authToken);
        return repository.findByUserName(userName);
    }
}
