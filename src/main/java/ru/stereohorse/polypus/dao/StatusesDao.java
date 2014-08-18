package ru.stereohorse.polypus.dao;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stereohorse.polypus.model.Status;

@Repository
public class StatusesDao {
    @Autowired
    private SessionFactory sessionFactory;


    public Status getByValue(String value) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Status.class);
        criteria.add(Restrictions.eq("value", value));

        return (Status) criteria.uniqueResult();
    }
}
