package org.demo.models;

import lombok.Data;

import javax.persistence.*;

/**
 * @author dshvedchenko on 5/27/16.
 */
@Data
@Entity
@Table(name ="GANRE")
public class Ganre {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Integer id;

    @Column (name = "TITLE")
    private String title;
}
