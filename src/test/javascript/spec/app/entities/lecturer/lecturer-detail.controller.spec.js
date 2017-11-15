'use strict';

describe('Controller Tests', function() {

    describe('Lecturer Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockLecturer, MockDayTime, MockCourse, MockSession;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockLecturer = jasmine.createSpy('MockLecturer');
            MockDayTime = jasmine.createSpy('MockDayTime');
            MockCourse = jasmine.createSpy('MockCourse');
            MockSession = jasmine.createSpy('MockSession');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Lecturer': MockLecturer,
                'DayTime': MockDayTime,
                'Course': MockCourse,
                'Session': MockSession
            };
            createController = function() {
                $injector.get('$controller')("LecturerDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'schedulingProjectApp:lecturerUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
