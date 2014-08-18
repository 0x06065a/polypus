describe('tasks service', function() {
    var tasksService;
    var journalNameCookie;
    var $httpBackend;

    beforeEach(function() {
        module('polypusModule');

        inject(function(_tasksService_, _$httpBackend_, JOURNAL_NAME_COOKIE) {
            tasksService = _tasksService_;
            journalNameCookie = JOURNAL_NAME_COOKIE;
            $httpBackend = _$httpBackend_;
        });
    });

    it('should exist', function() {
        expect(tasksService).toBeDefined();
    });

    it('should create new task', function() {
        var expectedTask = {
            name: 'awesomeTask',
            somethingElse: 'awesomeTask'
        };
        $httpBackend.expectPOST('api/tasks', {journalId: '123', name: expectedTask.name}).respond(expectedTask);

        var createdTask = tasksService.createTask({id: '123'}, expectedTask.name);
        $httpBackend.flush();

        expect(createdTask.name).toEqual(expectedTask.name);
        expect(createdTask.somethingElse).toEqual(expectedTask.somethingElse);
    });
});
