package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.datasource.UserRepositoryJpa;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository repository;

    @Override
    public User registry(User user) {
        User savedUser = repository.save(user);
        return user;
    }
}
