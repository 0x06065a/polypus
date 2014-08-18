describe('journals controller', function() {
    var scope, controller;

    beforeEach(function() {
        module('polypusModule');
    });

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        controller = $controller('journalsController', {$scope: scope});
    }));

    it('should exist', function() {
        expect(controller).toBeDefined();
    });

    it('should show current date', function() {
        expect(scope.date.current - new Date().getTime()).toBeLessThan(1000);
    });
});
