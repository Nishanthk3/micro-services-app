package com.example.webapp.service;

import com.example.webapp.entity.playground.AuthorDO;
import com.example.webapp.repository.playground.AuthorRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    private DozerBeanMapper mapper = new DozerBeanMapper();

    public List<Author> getAllAuthors() {
        List<AuthorDO> authorDOS = authorRepository.findAll();
        List<Author> result = new ArrayList<>();
        for (AuthorDO authorDO : authorDOS) {
            result.add(mapper.map(authorDO, Author.class));
        }
        return result;
    }
}
