package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserPO, Long> {

    UserPO findByUserNameAndPassword(String userName, String password);
    UserPO findByUserName(String userName);
}
