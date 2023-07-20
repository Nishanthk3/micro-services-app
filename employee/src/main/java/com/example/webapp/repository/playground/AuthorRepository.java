package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.AuthorDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorDO, Long> {
    Collection<AuthorDO> findByName(String authorName);
}
