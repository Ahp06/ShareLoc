package shareloc.dao;

import shareloc.model.Colocation;
import shareloc.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }
}
