package com.example.webapp.entity.playground;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book", schema = "playground")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column(name = "name") We don't have to specifically mention the
     * column name unless the name has any special characters. The field name is the column name by default
     */
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "author_id")
    private AuthorDO author;

}
