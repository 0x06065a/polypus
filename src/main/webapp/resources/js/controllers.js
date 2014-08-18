angular.module('polypusModule.controllers', ['polypusModule.services'])

    .controller('journalsController', function($scope, journalsService) {
        $scope.date = {};
        $scope.date.current = new Date().getTime();

        $scope.loadDesk = function() {};

        $scope.journal = journalsService.getCurrentJournal($scope.date.current);
    })

    .controller('tasksController', function($scope, tasksService) {
        $scope.addStep = function() {};

        $scope.addTask = function() {
            tasksService.createTask($scope.journal, $scope.newTaskName);
        };
    });
