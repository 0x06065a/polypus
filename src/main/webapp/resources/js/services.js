angular.module('polypusModule.services', ['ngCookies', 'ngResource'])

    .factory('tasksFactory', function($resource) {
        return $resource('api/tasks/:id');
    })

    .factory('journalsFactory', function($resource) {
        return $resource('api/journals/:journalName/:dt');
    })

    .factory('stepsFactory', function($resource) {
        return $resource('api/steps/:id');
    })

    .service('journalsService', function(journalsFactory, $cookieStore, JOURNAL_NAME_COOKIE) {
        return {
            getCurrentJournal: function(currentDate) {
                var currentJournalName = $cookieStore.get(JOURNAL_NAME_COOKIE);

                if (currentJournalName) {
                    return journalsFactory.get({
                        journalName: currentJournalName,
                        dt: currentDate});
                } else {
                    var journal = journalsFactory.save();

                    journal.$promise.then(function(createdJournal) {
                        $cookieStore.put(JOURNAL_NAME_COOKIE, createdJournal.name);
                    });

                    return journal;
                }
            }
        };
    })

    .service('tasksService', function(tasksFactory) {
        return  {
            createTask: function(journal, taskName) {
                return tasksFactory.save({
                    journalId: journal.id,
                    taskName: taskName
                });
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
            }
        };
    });
