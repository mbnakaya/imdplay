package io.mbnakaya.imdplay.datasource.port;

import io.mbnakaya.imdplay.domain.User;

public interface UserRepository {
    User save(User user);
    User login(User user);
}
