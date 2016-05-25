package org.demo.dao;

import org.demo.models.Audio;
import org.demo.models.Author;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author dshvedchenko on 5/24/16.
 */
public class AudioDaoImpl implements AudioDao{

    private final Connection connection;

    public AudioDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Audio save(Audio audio) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO AUDIOS (TITLE, YEAR,DURATION) VALUES (?,?,?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,audio.getTitle());
            statement.setInt(2, audio.getYear());
            statement.setInt(3, audio.getDuration());
            int rows = statement.executeUpdate();
            if (rows == 1) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    audio.setId(rs.getInt(1));
                }

                rs.close();

                //TODO save Authors_Audios
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audio;
    }

    @Override
    public boolean update(Audio audio) {
        PreparedStatement statement = null;
        int rows = 0;
        try {
            statement = connection.prepareStatement("UPDATE AUDIOS set " +
                    "TITLE = ?, " +
                    "YEAR = ?, " +
                    "DUARION = ? WHERE ID = ? ");
            statement.setString(1,audio.getTitle());
            statement.setInt(2, audio.getYear());
            statement.setInt(3, audio.getDuration());
            statement.setInt(4,audio.getId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows == 1;
    }

    @Override
    public Audio read(int id) {
        Audio audio = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT TITLE, YEAR, DURATION FROM AUDIOS WHERE ID = ? ");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                audio = new Audio();
                audio.setId(id);
                audio.setTitle(rs.getString(1));
                audio.setYear(rs.getInt(2));
                audio.setDuration(rs.getInt(3));
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audio;
    }

    @Override
    public Set<Audio> getAll() {
        Set<Audio> audios = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT au.ID, au.TITLE, au.YEAR, au.DURATION FROM AUDIOS au ");

            ResultSet rs = statement.executeQuery();
            audios = new LinkedHashSet();
            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getInt("au.ID"));
                audio.setTitle(rs.getString("au.TITLE"));
                audio.setYear(rs.getInt("au.YEAR"));
                audio.setDuration(rs.getInt("au.DURATION"));
                audios.add(audio);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audios;
    }

    @Override
    public boolean delete(Audio audio) {
        PreparedStatement statement = null;
        int rows = 0;
        try {
            statement = connection.prepareStatement("DELETE FROM AUDIOS WHERE ID = ? ");
            statement.setInt(1,audio.getId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows == 1;
    }

    @Override
    public boolean deleteAll() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM AUDIOS");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public Set<Audio> getAudiosByAuthor(Author author) {
        Set<Audio> audios = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT au.ID, au.TITLE, au.YEAR, au.DURATION FROM AUDIOS au \n" +
                    "INNER AUTHORS_AUDIOS aa ON au.id = aa.AUDIO_ID WHERE aa.AUTHOR_ID = ? ");
            statement.setInt(1, author.getId());
            ResultSet rs = statement.executeQuery();
            audios = new LinkedHashSet();
            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getInt(1));
                audio.setTitle(rs.getString(2));
                audio.setYear(rs.getInt(3));
                audio.setDuration(rs.getInt(4));
                audios.add(audio);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audios;
    }

    @Override
    public Set<Audio> getAudiosByAuthorByYear(Author author, int year) {
        Set<Audio> audios = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT au.ID, au.TITLE, au.DURATION FROM AUDIOS au \n" +
                    "INNER AUTHORS_AUDIOS aa ON au.id = aa.AUDIO_ID WHERE aa.AUTHOR_ID = ? AND au.YEAR = ?");
            statement.setInt(1, author.getId());
            statement.setInt(2, year);
            ResultSet rs = statement.executeQuery();
            audios = new LinkedHashSet();
            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getInt(1));
                audio.setTitle(rs.getString(2));
                audio.setYear(year);
                audio.setDuration(rs.getInt(3));
                audios.add(audio);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audios;
    }

    @Override
    public List<Audio> getAudiosForYearWithAuthors(int year) {
        List<Audio> audios = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT au.ID, au.TITLE, au.DURATION, at.ID, at.FIRST_NAME, at.LAST_NAME, at.BIRTHDAY FROM AUDIOS au \n" +
                    "INNER JOIN AUTHORS_AUDIOS aa ON au.id = aa.AUDIO_ID \n" +
                    "INNER JOIN AUTHORS at ON aa.AUTHOR_ID = at.ID \n" +
                    "WHERE au.YEAR = ?");
            statement.setInt(1, year);
            ResultSet rs = statement.executeQuery();
            audios = new LinkedList();
            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getInt("au.ID"));
                audio.setTitle(rs.getString("au.TITLE"));
                audio.setYear(year);
                audio.setDuration(rs.getInt("au.DURATION"));

                Author author = new Author();
                author.setId(rs.getInt("at.ID"));
                author.setFirst_name(rs.getString("at.FIRST_NAME"));
                author.setLast_name(rs.getString("at.LAST_NAME"));
                author.setBirthdate(rs.getDate("at.BIRTHDAY"));

                int foundInd = audios.indexOf(audio);
                if( foundInd != -1 ) {
                    Audio oldOne = audios.get(foundInd);
                    oldOne.getAuthors().add(author);
                } else {
                    audios.add(audio);
                    audio.getAuthors().add(author);
                };
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audios;
    }

    @Override
    public Set<Audio> getAudiosFromEldestAuthor() {
        Set<Audio> audios = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();;

            ResultSet rs = statement.executeQuery("SELECT au.ID, au.TITLE, au.YEAR, au.DURATION, aa.AUTHOR_ID FROM AUDIOS au \n" +
                    "INNER AUTHORS_AUDIOS aa ON au.id = aa.AUDIO_ID WHERE aa.AUTHOR_ID = ( SELECT ID FROM AUTHORS WHERE BIRTHDAY = (SELECT MIN(BIRTHDAY) FROM AUTHORS) limit 1) ");
            audios = new LinkedHashSet();
            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getInt("au.ID"));
                audio.setTitle(rs.getString("au.TITLE"));
                audio.setYear(rs.getInt("au.YEAR"));
                audio.setDuration(rs.getInt("au.DURATION"));
                audios.add(audio);
                Author author = new Author();
                author.setId(rs.getInt("aa.AUTHOR_ID"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return audios;
    }
}
