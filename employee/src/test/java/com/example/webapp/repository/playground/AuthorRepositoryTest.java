package com.example.webapp.repository.playground;

import com.example.webapp.entity.playground.AuthorDO;
import com.example.webapp.entity.playground.BookDO;
import com.example.webapp.entity.playground.EmployeeDO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    @Transactional
    public void testAuthor() {
        for (AuthorDO authorDO : this.authorRepository.findAll()) {
            List<BookDO> bookDO = authorDO.getBook();
            for (BookDO bookDO1 : bookDO) {
                System.out.print("[Author] ID: " + authorDO.getId() + ", Name: " + authorDO.getName() + " |  [Book] ID: " + bookDO1.getId() + ", Name: " + bookDO1.getName());
                System.out.println();
            }
            System.out.println();

        }
    }
}
