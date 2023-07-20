package com.example.webapp.repository.account;

import com.example.webapp.entity.account.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {
    Collection<UserDO> findByFirstName(String userFirstName);
}
