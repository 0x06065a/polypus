package ru.stereohorse.polypus.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stereohorse.polypus.dao.PrioritiesDao;
import ru.stereohorse.polypus.dao.StatusesDao;
import ru.stereohorse.polypus.dao.StepsDao;
import ru.stereohorse.polypus.dao.TasksDao;
import ru.stereohorse.polypus.model.Priority;
import ru.stereohorse.polypus.model.Status;
import ru.stereohorse.polypus.model.Step;
import ru.stereohorse.polypus.model.Task;


@Service
public class StepsService {

    @Autowired
    private TasksDao tasksDao;

    @Autowired
    private StatusesDao statusesDao;

    @Autowired
    private PrioritiesDao prioritiesDao;

    @Autowired
    private StepsDao stepsDao;


    @Transactional
    public Step createStep(Integer taskId, String stepName) {
        Task task = tasksDao.getById(taskId);
        if (task == null) {
            throw new RuntimeException("No such task");
        }

        Step step = new Step();
        step.setTask(task);
        step.setName(stepName);
        step.setPriority(prioritiesDao.getByValue(Priority.DEFAULT_VALUE));
        step.setStatus(statusesDao.getByValue(Status.DEFAULT_VALUE));

        step.setId(stepsDao.save(step));

        return step;
    }

    @Transactional
    public Step getById(Integer stepId) {
        return stepsDao.getById(stepId);
    }
}
