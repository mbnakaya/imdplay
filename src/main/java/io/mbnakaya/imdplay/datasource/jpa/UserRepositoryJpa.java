package io.mbnakaya.imdplay.datasource.jpa;

import io.mbnakaya.imdplay.datasource.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserPO, Long> {

    UserPO findByUserNameAndPassword(String userName, String password);
    UserPO findByUserName(String userName);
    @Query(value = "SELECT * FROM users LIMIT :top ORDER BY score DESC", nativeQuery = true)
    List<UserPO> getRanking(@Param("top") Integer top);
}
