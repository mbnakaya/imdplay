package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.po.UserPO;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserRepositoryJpa repositoryJpa;

    @Override
    public User save(User user) {
        return repositoryJpa.save(UserPO.toPO(user)).toDomain();
    }

    @Override
    public User login(User user) {
        return repositoryJpa
                .findByUserNameAndPassword(user.getUserName(), user.getPassword())
                .toDomain();
    }

    @Override
    public User findByUserName(String userName) {
        return repositoryJpa
                .findByUserName(userName)
                .toDomain();
    }
}
