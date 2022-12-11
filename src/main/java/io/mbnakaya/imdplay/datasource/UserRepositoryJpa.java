package io.mbnakaya.imdplay.datasource;

import io.mbnakaya.imdplay.datasource.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJpa extends JpaRepository<UserPO, Long> {}
