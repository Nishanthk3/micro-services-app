package com.example.webapp.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "line")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Line implements Serializable {
    private String id;
    private String name;
    private boolean status;
    private List<Product> products;

    @Override
    public String toString() {
        return "Line{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", products=" + products +
                '}';
    }
}
