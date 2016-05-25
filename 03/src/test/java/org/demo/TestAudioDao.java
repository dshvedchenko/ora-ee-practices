package org.demo;

import org.demo.dao.AudioDao;
import org.demo.dao.AudioDaoImpl;
import org.demo.db.DataSource;
import org.demo.models.Audio;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author dshvedchenko on 5/25/16.
 */
public class TestAudioDao {
    static DataSource dataSource;
    public AudioDao audioDao;
    Connection conn;

    @BeforeClass
    public static void initSuite() throws SQLException {
        dataSource = DataSource.INSTANCE;
        assertNotNull(dataSource);
    }

    @AfterClass
    public static void closeSuite() {
        dataSource.destroy();
    }

    @Before
    public void initRes() throws SQLException {
        conn = dataSource.getConnection();
        audioDao = new AudioDaoImpl(conn);
    }

    @After
    public void releaseRes() throws SQLException {
        conn.close();
    }

    @Test
    public void TestSave() {
        Audio audio = new Audio();
        audio.setYear(1922);
        audio.setTitle("Kukuku");
        audio.setDuration(1000);
        audioDao.save(audio);
        Assert.assertNotNull(audio.getId());
    }

    @Test
    public void TestDelete() {
        Set<Audio> audios = audioDao.getAll();
        for(Audio audio : audios ) {
            boolean res = audioDao.delete(audio);
            Assert.assertEquals(true, res);
            break;
        };
    }

    @Test
    public void TestDeleteAll() {
        Audio audio = new Audio();
        boolean res = audioDao.deleteAll();
        Assert.assertEquals(true, res);
    }
}
