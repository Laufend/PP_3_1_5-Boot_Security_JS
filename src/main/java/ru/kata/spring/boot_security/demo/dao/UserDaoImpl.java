package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void removeUser(int id) {
        entityManager.remove(getUser(id));
    }
    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }
    public User getUser(String login) {
        List<User> list = entityManager.createQuery("select distinct user from User user join fetch user.authorities roles where user.username=?1", User.class)
                .setParameter(1, login).getResultList();
        System.out.println(list);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    @Override
    public List<User> getUserList() {
        return entityManager
                .createQuery("select distinct user from User user join fetch user.authorities roles order by user.id", User.class).getResultList();
    }
}
