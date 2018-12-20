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

    /*public List<Colocation> getMyColocations(String email){
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Colocation> query = cb.createQuery(Colocation.class);
        Root<Colocation> colocs = query.from(Colocation.class);

        Join<User, Colocation> myColocs = colocs.join("colocs");
        query.select(colocs).where(cb.equal(teacher.get("firstName"), "prasad"));
    }*/
}
