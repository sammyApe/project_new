'use strict';

describe('Controller Tests', function() {

    describe('DayTime Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDayTime, MockLecturer, MockTime;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDayTime = jasmine.createSpy('MockDayTime');
            MockLecturer = jasmine.createSpy('MockLecturer');
            MockTime = jasmine.createSpy('MockTime');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DayTime': MockDayTime,
                'Lecturer': MockLecturer,
                'Time': MockTime
            };
            createController = function() {
                $injector.get('$controller')("DayTimeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'schedulingProjectApp:dayTimeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
