package org.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
@Data
@Entity
@Table ( name = "AUTHORS")
public class Author {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "FIRST_NAME")
    private String first_name;

    @Column (name = "LAST_NAME")
    private String last_name;

    @Column (name = "BIRTHDAY")
    private Date birthday;

    @ManyToMany ( mappedBy = "authors")
    private Set<Audio> audios;
}
