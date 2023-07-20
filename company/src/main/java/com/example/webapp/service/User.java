package com.example.webapp.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "user")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class User {
    private Long id;
    private String firstName;
    private String lastName;

}
