package ru.stereohorse.polypus.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.stereohorse.polypus.model.Task;

@Repository
public class TasksDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Integer saveTask(Task task) {
        return (Integer) sessionFactory.getCurrentSession().save(task);
    }
}
