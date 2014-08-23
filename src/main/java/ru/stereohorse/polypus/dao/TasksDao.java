package ru.stereohorse.polypus.dao;


import org.hibernate.Criteria;
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
    public List<Task> getForPeriod(Journal journal, Date startDate, Date endDate) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("journal", journal));
        criteria.add(Restrictions.ge("date", startDate));
        criteria.add(Restrictions.le("date", endDate));

        return criteria.list();
    }

    public Task getById(Integer taskId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class);
        criteria.add(Restrictions.eq("id", taskId));

        return (Task) criteria.uniqueResult();
    }
}
