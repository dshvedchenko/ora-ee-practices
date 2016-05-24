package org.demo.dao;

import org.demo.models.Audio;
import org.demo.models.Author;

import java.util.Date;
import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
public interface AuthorDao {
    Author save(Author author);
    boolean update(Author author);
    Author read(int id);
    boolean delete(Author author);

    Set<Author> getAuthorsWithBirthdayswithinDates(Date startDate, Date endDate);
    Set<Author> getAuthorsForGivenBirhtsDays(Set<Date> birthDates);
}
