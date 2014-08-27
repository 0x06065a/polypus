package ru.stereohorse.polypus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteTask(@PathVariable("id") Integer id) {
        tasksService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public void finishTask(@PathVariable("id") Integer id, @RequestParam String action) {
        switch (Action.valueOf(action)) {
            case FINISH:
                tasksService.finishById(id);
                break;
        }
    }

    private static enum Action {
        FINISH
    }
}
