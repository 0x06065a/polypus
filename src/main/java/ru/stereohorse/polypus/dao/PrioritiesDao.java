package ru.stereohorse.polypus.dao;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stereohorse.polypus.model.Priority;

@Repository
public class PrioritiesDao {
    @Autowired
    private SessionFactory sessionFactory;


    public Priority getByValue(String value) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Priority.class);
        criteria.add(Restrictions.eq("value", value));

        return (Priority) criteria.uniqueResult();
    }
}
