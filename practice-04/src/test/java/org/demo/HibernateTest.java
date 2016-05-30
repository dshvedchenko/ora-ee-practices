package org.demo;

import org.demo.models.Audio;
import org.demo.models.Ganre;
import org.demo.models.Group;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class HibernateTest {

    SessionFactory factory = null;
    Session session = null;
    Transaction tx = null;

    @Before
    public void  initSession() {
        factory = HibernateUtil.getSessionFactory();
        session = factory.openSession();
    }

    @After
    public void downSession() {
        session.close();
    }

    @Test
    public void testConnection() {

        try {
            tx = session.beginTransaction();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            tx = session.beginTransaction();
            Audio audio = new Audio();
            audio.setDuration(1111);
            audio.setTitle("KuKU");
            audio.setYear(1977);
            session.save(audio);
            tx.commit();

            System.out.println(audio.getId());
            Assert.assertTrue(11 == audio.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateAUdioGroup() {
        try {
            tx = session.beginTransaction();
            Audio audio = new Audio();
            audio.setDuration(100);
            audio.setTitle("KuKU 1 song");
            audio.setYear(1977);

            Group group = new Group();
            group.setTitle("Gh");

      //      session.save(group);

            audio.setGroup(group);

            Ganre ganre = new Ganre();
            ganre.setTitle("Disco");
            session.save(ganre);

            audio.setGanre(ganre);

            session.save(audio);
            tx.commit();

            System.out.println(audio.getId());
            Assert.assertNotNull(audio.getId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllAudio() {
        try {
            Criteria audioCrit = session.createCriteria(Audio.class);
            List<Audio> audios = audioCrit.list();
            Assert.assertTrue(audios.size() > 0);

            for (Audio audio : audios) {
                System.out.printf("Audio %s : %s", audio.getTitle(), audio.getGanre().getTitle());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
