package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements  UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }


    @Override
    public void updateUser(int id, User userForUpdate) {
        User user = entityManager.find(User.class, id);
        user.setName(userForUpdate.getName());
        user.setSurname(userForUpdate.getSurname());
        user.setProfession(userForUpdate.getProfession());
        user.setEmail(userForUpdate.getEmail());
        entityManager.persist(user);
    }


}
