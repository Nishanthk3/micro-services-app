package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BookRepository extends JpaRepository<BookDO, Long> {
    Collection<BookDO> findByName(String bookName);
}
