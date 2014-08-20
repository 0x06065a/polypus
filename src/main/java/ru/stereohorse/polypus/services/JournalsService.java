package ru.stereohorse.polypus.services;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stereohorse.polypus.dao.JournalsDao;
import ru.stereohorse.polypus.dao.TasksDao;
import ru.stereohorse.polypus.model.Journal;
import ru.stereohorse.polypus.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JournalsService {
    @Autowired
    private JournalsDao journalsDao;

    @Autowired
    private TasksDao tasksDao;

    @Autowired
    private DateService dateService;


    @Transactional
    public Journal createJournal() {
        return createJournal(null);
    }


    @Transactional
    public Journal getOrCreateJournal(String name, Date date) {
        Journal journal = journalsDao.getJournalByName(name);
        if (journal != null) {
            Date startDate = dateService.getStartOfDay(date);
            Date endDate = dateService.getEndOfDay(date);

            List<Task> detachedTasks = new ArrayList<Task>();
            for (Task task : tasksDao.getTasksForPeriod(journal, startDate, endDate)) {
                detachedTasks.add(new Task(task));
            }
            journal.setTasks(detachedTasks);

            return journal;
        }

        return createJournal(name);
    }

    private Journal createJournal(String name) {
        Journal journal = new Journal();

        name = Strings.isNullOrEmpty(name) ? UUID.randomUUID().toString() : name;
        journal.setName(name);

        journal.setId(journalsDao.saveJournal(journal));

        return journal;
    }
}
