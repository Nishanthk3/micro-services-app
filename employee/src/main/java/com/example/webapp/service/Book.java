package com.example.webapp.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "book")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class Book {

    private Long id;
    private String name;
    private Author auth;

}
