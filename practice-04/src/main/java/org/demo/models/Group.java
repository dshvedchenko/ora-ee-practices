package org.demo.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author dshvedchenko on 5/27/16.
 */
@Data
@Entity
@Table( name = "GROUPS",
uniqueConstraints = @UniqueConstraint(name = "unique_title", columnNames = {"TITLE"}))
public class Group {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column (name = "ID" )
    private Integer id;


    @Column(name = "TITLE", unique = true)
    private String title;

    @OneToMany (mappedBy = "group")
    Set<Audio> audios;
}
