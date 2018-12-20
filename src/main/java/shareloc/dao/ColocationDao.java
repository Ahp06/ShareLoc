package shareloc.dao;

import shareloc.model.Colocation;
import shareloc.model.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class ColocationDao extends AbstractDao<Colocation> {

    public ColocationDao() {
        super(Colocation.class);
    }
}
