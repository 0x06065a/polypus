package ru.stereohorse.polypus.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stereohorse.polypus.model.Journal;


@Repository
public class JournalsDao {
    @Autowired
    private SessionFactory sessionFactory;


    public Integer save(Journal journal) {
        return (Integer) sessionFactory.getCurrentSession().save(journal);
    }

    public Journal getByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Journal.class);
        criteria.add(Restrictions.eq("name", name));

        return (Journal) criteria.uniqueResult();
    }

    public Journal getById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Journal.class);
        criteria.add(Restrictions.eq("id", id));

        return (Journal) criteria.uniqueResult();
    }
}
