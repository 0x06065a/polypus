angular.module('polypusModule.services', ['ngCookies', 'ngResource'])

    .factory('tasksFactory', function($resource) {
        return $resource('api/tasks/:id', {id: '@id'}, {
            finish: {method: 'PUT', params: {
                action: 'FINISH'
            }}
        });
    })

    .factory('journalsFactory', function($resource) {
        return $resource('api/journals/:journalName/:dt');
    })

    .factory('stepsFactory', function($resource) {
        return $resource('api/steps/:id', {id: '@id'}, {
            finish: {method: 'PUT', params: {
                action: 'FINISH'
            }}
        });
    })

    .service('journalsService', function(journalsFactory, $cookieStore, JOURNAL_NAME_COOKIE) {
        return {
            getCurrentJournal: function(currentDate) {
                return this.getJournal($cookieStore.get(JOURNAL_NAME_COOKIE), currentDate);
            },
            getJournal: function(name, currentDate) {
                var journal = journalsFactory.get({journalName: name, dt: currentDate});

                journal.$promise.then(function(createdJournal) {
                    $cookieStore.put(JOURNAL_NAME_COOKIE, createdJournal.name);
                });

                return journal;
            }
        };
    })

    .service('tasksService', function(tasksFactory) {
        return  {
            createTask: function(journal, taskName) {
                return tasksFactory.save({
                    // TODO: should send time
                    journalId: journal.id,
                    taskName: taskName
                });
            },
            deleteTask: function(task) {
                return tasksFactory.delete(task).$promise;
            },
            finishTask: function(task) {
                return tasksFactory.finish(task).$promise;
            }
        }
    })

    .service('stepsService', function(stepsFactory) {
        return {
            createStep: function(task, stepName) {
                return stepsFactory.save({
                    taskId: task.id,
                    stepName: stepName
                });
            },
            deleteStep: function(step) {
                return stepsFactory.delete(step).$promise;
            },
            finishStep: function(step) {
                return stepsFactory.finish(step).$promise;
            }
        };
    })

    .service('utils', function() {
        return {
            spliceById: function(array, element) {
                for (var i = 0; i < array.length; i++) {
                    if (array[i].id == element.id) {
                        array.splice(i, 1);
                        return;
                    }
                }
            }
        };
    });
