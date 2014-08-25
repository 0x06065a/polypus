package ru.stereohorse.polypus.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
