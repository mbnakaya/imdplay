package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.po.UserPO;
import io.mbnakaya.imdplay.datasource.port.UserRepository;
import io.mbnakaya.imdplay.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa repositoryJpa;

    public UserRepositoryImpl(UserRepositoryJpa repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    @Override
    public User save(User user) {
        return repositoryJpa.save(UserPO.toPO(user)).toDomain();
    }
}
