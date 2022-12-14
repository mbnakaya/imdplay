package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.jpa.UserRepositoryJpa;
import io.mbnakaya.imdplay.datasource.po.UserPO;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import io.mbnakaya.imdplay.domain.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserRepositoryJpa repositoryJpa;

    public UserRepositoryImpl(UserRepositoryJpa bean) {
        this.repositoryJpa = bean;
    }

    @Override
    public User save(User user) {
        return repositoryJpa.save(UserPO.toPO(user)).toDomain();
    }

    @Override
    public User login(String userName, String password) {
        try {
            return repositoryJpa
                    .findByUserNameAndPassword(userName, password)
                    .toDomain();
        } catch (NullPointerException e) {
            throw new UnauthorizedException();
        }
    }

    @Override
    public User findByUserName(String userName) {
        return repositoryJpa
                .findByUserName(userName)
                .toDomain();
    }

    @Override
    public List<User> getRanking(Integer top) {
        return repositoryJpa.getRanking(top).stream().map(UserPO::toDomain).toList();
    }
}
