package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.AuthorDO;
import com.example.webapp.entity.playground.BookDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @Transactional
    public void testBook() {
        for (BookDO bookDO : this.bookRepository.findAll()) {
            AuthorDO authorDO = bookDO.getAuthor();
            System.out.println("[Book] ID: " + bookDO.getId() + ", Name: " + bookDO.getName() + ", [Author] ID: " + authorDO.getId() + ", Name:" + authorDO.getName());

        }
    }
}
