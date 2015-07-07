package de.svenbunge.hibernate.mergeissue;

import de.svenbunge.hibernate.mergeissue.model.Entry;
import de.svenbunge.hibernate.mergeissue.model.Parent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DeleteFromCollectionIssueTest.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootApplication
public class DeleteFromCollectionIssueTest {

    @PersistenceUnit
    private EntityManagerFactory emFactory;

    private final Parent p = new Parent();

    private EntityManager em;

    @Before
    public void before() {
        em = emFactory.createEntityManager();

        em.getTransaction().begin();
        em.persist(p);
        p.getMyList().add(new Entry("A"));
        p.getMyList().add(new Entry("B"));
        p.getMyList().add(new Entry("C"));
        p.getMyList().add(new Entry("D"));
        em.merge(p);
        em.getTransaction().commit();
    }

    @Test
    public void testSetup() {
        assertThat(em.find(Parent.class, p.getId()).getMyList(), hasSize(4));
    }

    @Test
    public void deleteFirstElementInList() {
        em.getTransaction().begin();
        final Parent parent = em.find(Parent.class, p.getId());
        parent.getMyList().remove(0);
        em.merge(parent);
        em.getTransaction().commit();
        assertThat(em.find(Parent.class, p.getId()).getMyList(), hasSize(3));
    }
    @Test
    public void deleteSecondElementInList() {
        em.getTransaction().begin();
        final Parent parent = em.find(Parent.class, p.getId());
        parent.getMyList().remove(1);
        em.merge(parent);
        em.getTransaction().commit();
        assertThat(em.find(Parent.class, p.getId()).getMyList(), hasSize(3));
    }
    @Test
    public void deleteThirdElementInList() {
        em.getTransaction().begin();
        final Parent parent = em.find(Parent.class, p.getId());
        parent.getMyList().remove(2);
        em.merge(parent);
        em.getTransaction().commit();
        assertThat(em.find(Parent.class, p.getId()).getMyList(), hasSize(3));
    }
    @Test
    public void deleteLastElementInList() {
        em.getTransaction().begin();
        final Parent parent = em.find(Parent.class, p.getId());
        parent.getMyList().remove(3);
        em.merge(parent);
        em.getTransaction().commit();
        assertThat(em.find(Parent.class, p.getId()).getMyList(), hasSize(3));
    }
}