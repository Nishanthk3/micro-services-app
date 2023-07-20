package com.example.webapp.service;

import com.example.webapp.entity.playground.BookDO;
import com.example.webapp.repository.playground.BookRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private DozerBeanMapper mapper = new DozerBeanMapper();

    public List<Book> getAllBooks() {
        List<BookDO> bookDOS = bookRepository.findAll();
        List<Book> result = new ArrayList<>();
        for (BookDO bookDO : bookDOS) {
            Book book = mapper.map(bookDO, Book.class);
            Author author = Author.builder().name(bookDO.getAuthor().getName()).id(bookDO.getAuthor().getId()).build();
            book.setAuth(author);
            result.add(book);
        }
        return result;
    }
}
