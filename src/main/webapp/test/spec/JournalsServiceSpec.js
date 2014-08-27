describe('journals service', function() {
    var journalsService;
    var mockCookieStore;
    var journalNameCookie;
    var $httpBackend;

    beforeEach(function() {
        module('polypusModule');

        inject(function(_journalsService_, $cookieStore, _$httpBackend_, JOURNAL_NAME_COOKIE) {
            journalsService = _journalsService_;
            mockCookieStore = $cookieStore;
            journalNameCookie = JOURNAL_NAME_COOKIE;
            $httpBackend = _$httpBackend_;
        });
    });

    it('should exist', function() {
        expect(journalsService).toBeDefined();
    });

    it('should fetch journal name from cookies', function() {
        var expectedJournal = {
            name: 'awesomeJournal'
        };

        mockCookieStore.put(journalNameCookie, expectedJournal.name);

        $httpBackend.expectGET('api/journals/' + expectedJournal.name + '/123').respond(expectedJournal);

        var currentJournal = journalsService.getCurrentJournal('123');

        $httpBackend.flush();

        expect(currentJournal.name).toEqual(expectedJournal.name);
        expect(mockCookieStore.get(journalNameCookie)).toEqual(expectedJournal.name);
    });

    it('should receive undefined for not existing cookie', function() {
        expect(mockCookieStore.get('something')).toBeUndefined();
    });

    it('should create new journal if don\'t have cookies', function() {
        var createdJournal = {
            name: 'superAwesomeJournal'
        };

        $httpBackend.expectGET('api/journals', undefined).respond(createdJournal);

        var currentJournal = journalsService.getCurrentJournal();

        $httpBackend.flush();

        expect(currentJournal.name).toEqual(createdJournal.name);
        expect(mockCookieStore.get(journalNameCookie)).toEqual(createdJournal.name);
    });
});
