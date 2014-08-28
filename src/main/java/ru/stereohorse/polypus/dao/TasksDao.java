package ru.stereohorse.polypus.dao;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stereohorse.polypus.model.Journal;
import ru.stereohorse.polypus.model.Task;

import java.util.Date;
import java.util.List;

@Repository
public class TasksDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Integer save(Task task) {
        return (Integer) sessionFactory.getCurrentSession().save(task);
    }

    @SuppressWarnings("unchecked")
    public List<Task> getActiveForPeriod(Journal journal, Date startDate, Date endDate) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from ru.stereohorse.polypus.model.Task task " +
                "left join fetch task.steps step " +
                "join fetch task.priority " +
                "join fetch task.status " +
                "where task.date >= :startDate " +
                "  and task.date < :endDate " +
                "  and task.journal = :journal" +
                "  and task.isDeleted = false " +
                "  and (step.isDeleted = false or step is null) " +
                "order by task.date, step.date")
                .setDate("startDate", startDate)
                .setDate("endDate", endDate)
                .setEntity("journal", journal);

        return query.list();
    }

    public Task getById(Integer taskId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("id", taskId));

        return (Task) criteria.uniqueResult();
    }

    public void setDeletedById(Integer id, boolean isDeleted) {
        String query = "update Task set isDeleted = :isDeleted where id = :taskId";
        sessionFactory.getCurrentSession().createQuery(query)
                .setInteger("taskId", id)
                .setBoolean("isDeleted", isDeleted)
                .executeUpdate();
    }

    public void setFinishedById(Integer id, boolean isFinished) {
        String query = "update Task set isFinished = :isFinished where id = :taskId";
        sessionFactory.getCurrentSession().createQuery(query)
                .setInteger("taskId", id)
                .setBoolean("isFinished", isFinished)
                .executeUpdate();
    }
}
