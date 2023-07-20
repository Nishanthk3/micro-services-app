package com.example.webapp.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "author")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Author {

    private Long id;
    private String name;
    @Builder.Default
    private List<Book> book = new ArrayList<>();
}
