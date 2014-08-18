package ru.stereohorse.polypus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stereohorse.polypus.dao.JournalsDao;
import ru.stereohorse.polypus.dao.PrioritiesDao;
import ru.stereohorse.polypus.dao.StatusesDao;
import ru.stereohorse.polypus.dao.TasksDao;
import ru.stereohorse.polypus.model.Priority;
import ru.stereohorse.polypus.model.Status;
import ru.stereohorse.polypus.model.Task;

@Service
public class TasksService {
    @Autowired
    private TasksDao tasksDao;

    @Autowired
    private JournalsDao journalsDao;

    @Autowired
    private StatusesDao statusesDao;

    @Autowired
    private PrioritiesDao prioritiesDao;


    @Transactional
    public Task createTask(Integer journalId, String name) {
        Task task = new Task();
        task.setName(name);

        task.setJournal(journalsDao.getJournalById(journalId));
        task.setStatus(statusesDao.getByValue(Status.DEFAULT_VALUE));
        task.setPriority(prioritiesDao.getByValue(Priority.DEFAULT_VALUE));

        task.setId(tasksDao.saveTask(task));

        return task;
    }
}
