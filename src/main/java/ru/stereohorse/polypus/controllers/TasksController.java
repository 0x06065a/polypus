package ru.stereohorse.polypus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.stereohorse.polypus.model.Task;
import ru.stereohorse.polypus.services.TasksService;

import java.util.Map;

@RestController
@RequestMapping("tasks")
public class TasksController {
    @Autowired
    private TasksService tasksService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public Task postTask(@RequestBody Map<String, Object> request) {
        Integer journalId = (Integer)request.get("journalId");
        String taskName = (String)request.get("name");

        // use copy constructor to avoid initialization of lazy collections
        return new Task(tasksService.createTask(journalId, taskName));
    }
}
