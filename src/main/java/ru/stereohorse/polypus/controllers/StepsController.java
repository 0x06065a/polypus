package ru.stereohorse.polypus.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.stereohorse.polypus.controllers.requests.StepCreateRequest;
import ru.stereohorse.polypus.model.Step;
import ru.stereohorse.polypus.services.StepsService;


@RestController
@RequestMapping("steps")
public class StepsController {

    @Autowired
    private StepsService stepsService;


    @RequestMapping(method = RequestMethod.POST, value = "")
    public Step createStep(@RequestBody StepCreateRequest request) {
        Integer stepId = stepsService.createStep(request.getTaskId(), request.getStepName()).getId();
        return stepsService.getById(stepId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteTask(@PathVariable("id") Integer id) {
        stepsService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public void finishTask(@PathVariable("id") Integer id, @RequestParam String action) {
        switch (Action.valueOf(action)) {
            case FINISH:
                stepsService.finishById(id);
                break;
        }
    }


    private static enum Action {
        FINISH
    }
}
