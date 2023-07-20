package com.example.webapp.entity.playground;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author", schema = "playground")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(name = "name") We don't have to specifically mention the
     * column name unless the name has any special characters. The field name is the column name by default
     */
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BookDO> book = new ArrayList<>();
}
