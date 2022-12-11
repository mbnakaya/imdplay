package io.mbnakaya.imdplay.interactors;

import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.interactors.port.RegisterService;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Override
    public User registry(User user) {
        return user;
    }
}
