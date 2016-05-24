package org.demo.models;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
@Data
public class Author {
    private int id;
    private String first_name;
    private String last_name;
    private Date birthdate;

    private Set<Audio> audios;
}
