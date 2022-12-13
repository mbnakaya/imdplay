package io.mbnakaya.imdplay.datasource.port;

import io.mbnakaya.imdplay.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User login(String userName, String password);
    User findByUserName(String userName);
    List<User> getRanking(Integer top);
}
