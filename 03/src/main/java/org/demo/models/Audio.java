package org.demo.models;

import lombok.Data;

import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
@Data
public class Audio {
    private int id;
    private String title;
    private Integer year;
    private Integer duration;

    Set<Author> authors;
}
