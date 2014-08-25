package ru.stereohorse.polypus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.stereohorse.polypus.controllers.requests.TaskCreateRequest;
import ru.stereohorse.polypus.model.Task;
import ru.stereohorse.polypus.services.TasksService;

@RestController
@RequestMapping("tasks")
public class TasksController {
    @Autowired
    private TasksService tasksService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public Task postTask(@RequestBody TaskCreateRequest request) {
        Integer taskId = tasksService.createTask(request.getJournalId(), request.getTaskName()).getId();
        return tasksService.getById(taskId);
    }
}
