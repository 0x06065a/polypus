package ru.stereohorse.polypus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.stereohorse.polypus.model.Journal;
import ru.stereohorse.polypus.services.JournalsService;

import java.util.Date;

@RestController
@RequestMapping("journals")
public class JournalsController {
    @Autowired
    private JournalsService journalsService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public Journal postJournal() {
        return journalsService.createJournal();
    }

    @RequestMapping("{name}/{timestamp}")
    public Journal getJournal(@PathVariable("name") String name, @PathVariable("timestamp") Long timestamp) {
        return new Journal(journalsService.getOrCreateJournal(name, new Date(timestamp)));
    }
}
