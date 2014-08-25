package ru.stereohorse.polypus.dao;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stereohorse.polypus.model.Step;


@Repository
public class StepsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Integer save(Step step) {
        return (Integer) sessionFactory.getCurrentSession().save(step);
    }

    public Step getById(Integer stepId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Step.class);
        criteria.add(Restrictions.eq("id", stepId));

        return (Step) criteria.uniqueResult();
    }
}
