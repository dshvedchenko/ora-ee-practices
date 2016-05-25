package org.demo.dao;

import org.demo.models.Audio;
import org.demo.models.Author;

import java.util.List;
import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
public interface AudioDao {
    Audio save(Audio audio);
    boolean update(Audio audio);
    Audio read(int id);
    boolean delete(Audio audio);
    boolean deleteAll();

    Set<Audio> getAll();

    Set<Audio> getAudiosByAuthor(Author author);
    Set<Audio> getAudiosByAuthorByYear(Author author, int year);

    List<Audio> getAudiosForYearWithAuthors(int year);
    Set<Audio> getAudiosFromEldestAuthor();
}
