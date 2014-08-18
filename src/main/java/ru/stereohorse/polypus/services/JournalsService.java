package ru.stereohorse.polypus.services;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stereohorse.polypus.dao.JournalsDao;
import ru.stereohorse.polypus.model.Journal;

import java.util.Date;
import java.util.UUID;

@Service
public class JournalsService {
    @Autowired
    private JournalsDao journalsDao;


    @Transactional
    public Journal createJournal() {
        return createJournal(null);
    }


    @Transactional
    public Journal getOrCreateJournal(String name, Date date) {
        Journal journal = journalsDao.getJournalByName(name);
        if (journal != null) {
            return journal;
        }

        return createJournal(name);
    }

    @Transactional
    public Journal getJournal(Integer id) {
        return journalsDao.getJournalById(id);
    }

    private Journal createJournal(String name) {
        Journal journal = new Journal();

        name = Strings.isNullOrEmpty(name) ? UUID.randomUUID().toString() : name;
        journal.setName(name);

        journal.setId(journalsDao.saveJournal(journal));

        return journal;
    }
}
