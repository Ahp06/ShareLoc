package dao;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class UserDao extends AbstractDao<User>{

    public UserDao() {
        super(User.class);
    }
}
