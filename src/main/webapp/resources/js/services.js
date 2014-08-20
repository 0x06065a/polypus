angular.module('polypusModule.services', ['ngCookies', 'ngResource'])

    .factory('tasksFactory', function($resource) {
        return $resource('api/tasks/:id');
    })

    .factory('journalsFactory', function($resource) {
        return $resource('api/journals/:journalName/:dt');
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

    .service('tasksService', function($resource, tasksFactory) {
        return  {
            createTask: function(journal, taskName) {
                var request = {
                    journalId: journal.id,
                    taskName: taskName
                };

                return tasksFactory.save(request);
            }
        }
    });
