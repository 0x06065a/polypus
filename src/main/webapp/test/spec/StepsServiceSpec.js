describe('steps service', function() {
    var stepsService;
    var $httpBackend;

    beforeEach(function() {
        module('polypusModule');

        inject(function(_stepsService_, _$httpBackend_) {
            stepsService = _stepsService_;
            $httpBackend = _$httpBackend_;
        });
    });

    it('should exist', function() {
        expect(stepsService).toBeDefined();
    });

    it('should add new step', function() {
        var expectedStep = {
            name: 'awesomeStep',
            somethingElse: 'somethingElse'
        };

        $httpBackend.expectPOST('api/steps', {taskId: '123', stepName: expectedStep.name}).respond(expectedStep);

        var createdStep = stepsService.createStep({id: '123'}, expectedStep.name);
        $httpBackend.flush();

        expect(createdStep.name).toEqual(expectedStep.name);
        expect(createdStep.somethingElse).toEqual(expectedStep.somethingElse);
    });
});