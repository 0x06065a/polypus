angular.module('polypusModule.controllers', ['polypusModule.services'])

    .controller('journalsController', function($scope, journalsService) {
        $scope.date = {};
        $scope.date.current = new Date().getTime();
        $scope.journal = journalsService.getCurrentJournal($scope.date.current);

        $scope.loadDesk = function() {
            $scope.journal = journalsService.getJournal($scope.journal.name, $scope.date.current);
        };
    })

    .controller('tasksController', function($scope, tasksService, utils) {
        $scope.addTask = function() {
            var task = tasksService.createTask($scope.journal, $scope.newTaskName);
            $scope.journal.tasks.push(task);
            $scope.newTaskName = '';
        };

        $scope.deleteTask = function(task) {
            tasksService.deleteTask(task).then(function() {
                utils.spliceById($scope.journal.tasks, task);
            });
        };

        $scope.finishTask = function(task) {
            tasksService.finishTask(task).then(function(){
                task.finished = true;
            });
        };
    })

    .controller('stepsController', function($scope, stepsService, utils) {
        $scope.addStep = function(task) {
            var step = stepsService.createStep(task, $scope.newStepName);
            task.steps.push(step);
            $scope.newStepName = '';
        };

        $scope.deleteStep = function(step) {
            stepsService.deleteStep(step).then(function() {
                utils.spliceById($scope.task.steps, step);
            });
        };

        $scope.finishStep = function(step) {
            stepsService.finishStep(step).then(function() {
                step.finished = true;
                console.log(step);
            });
        };
    });
