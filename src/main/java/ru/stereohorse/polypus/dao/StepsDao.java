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

    public void setDeletedById(Integer id, boolean isDeleted) {
        String query = "UPDATE Step SET isDeleted = :isDeleted WHERE id = :stepId";
        sessionFactory.getCurrentSession().createQuery(query)
                .setInteger("stepId", id)
                .setBoolean("isDeleted", isDeleted)
                .executeUpdate();
    }

    public void setFinishedById(Integer id, boolean isFinished) {
        String query = "UPDATE Step SET isFinished = :isFinished WHERE id = :stepId";
        sessionFactory.getCurrentSession().createQuery(query)
                .setInteger("stepId", id)
                .setBoolean("isFinished", isFinished)
                .executeUpdate();
    }
}
