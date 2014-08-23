angular.module('polypusModule.controllers', ['polypusModule.services'])

    .controller('journalsController', function($scope, journalsService) {
        $scope.date = {};
        $scope.date.current = new Date().getTime();

        $scope.loadDesk = function() {};

        $scope.journal = journalsService.getCurrentJournal($scope.date.current);
    })

    .controller('tasksController', function($scope, tasksService) {
        $scope.addTask = function() {
            var task = tasksService.createTask($scope.journal, $scope.newTaskName);
            $scope.journal.tasks.push(task);
            $scope.newTaskName = '';
        };
    })

    .controller('stepsController', function($scope, stepsService) {
        $scope.addStep = function(task) {
            console.log($scope);
            var step = stepsService.createStep(task, $scope.newStepName);
            task.steps.push(step);
            $scope.newStepName = '';
        };
    });
