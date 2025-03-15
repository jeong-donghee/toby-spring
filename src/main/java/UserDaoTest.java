import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1 = new User("weg9511", "정동희", "1q2w3e4r!");
        User user2 = new User("weg9512", "정동흐", "1q2w3e4r@");

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        dao.add(user2);
        assertEquals(dao.getCount(), 2);

        User userget1 = dao.get(user1.getId());
        assertEquals(userget1.getName(), user1.getName());
        assertEquals(userget1.getPassword(), user1.getPassword());

        User userge2 = dao.get(user2.getId());
        assertEquals(userge2.getName(), userge2.getName());
        assertEquals(userge2.getPassword(), userge2.getPassword());
    }

    @Test
    public void count() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("user1", "유저1", "password1");
        User user2 = new User("user2", "유저2", "password2");
        User user3 = new User("user3", "유저3", "password3");

        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        dao.add(user1);
        assertEquals(dao.getCount(), 1);

        dao.add(user2);
        assertEquals(dao.getCount(), 2);

        dao.add(user3);
        assertEquals(dao.getCount(), 3);
    }

    @Test
    public void getUserFailure() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);
        dao.deleteAll();
        assertEquals(dao.getCount(), 0);

        assertThrows(EmptyResultDataAccessException.class, () -> {
            dao.get("unknown_id");
        });
    }
}
