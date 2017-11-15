(function () {
        'use strict';
        angular.module('schedulingProjectApp').controller('ScheduleController', ScheduleController);
        ScheduleController.$inject = ['$scope', '$timeout', '$state', 'Schedule', 'Lecturer', 'Course'];

        function ScheduleController($scope, $state, $timeout, Schedule, Lecturer, Course, uiCalendarConfig) {
            var vm = this;
            vm.schedules = [];
            vm.lecturers = [];
            vm.courses = [];
            vm.events = [];

            loadAll();


            /* config object */
            vm.uiConfig = {
                calendar: {
                    height: 450,
                    editable: true,
                    header: {
                        left: 'month agendaWeek agendaDay'
                        , center: 'title'
                        , right: 'today prev,next'
                    },
                      droppable: true
                    , events: vm.events
                    , eventClick: vm.alertOnEventClick
                    , eventDrop: vm.alertOnDrop
                    , eventResize: vm.alertOnResize
                }
            };

            vm.onDragComplete = function (data, evt) {
             debugger;
           console.log("drag success, data:", data);
       }
            /* alert on eventClick */
            vm.alertOnEventClick = function (date, jsEvent, view) {
                vm.alertMessage = (date.title + ' was clicked ');
                console.log(vm.alertMessage);
            };
            /* alert on Drop */
            vm.alertOnDrop = function (event, delta, revertFunc, jsEvent, ui, view) {
                alert('Event Dropped to make dayDelta ');
                vm.alertMessage = ('Event Dropped to make dayDelta ' + delta);
                console.log(vm.alertMessage);
            };
            /* alert on Resize */
            vm.alertOnResize = function (event, delta, revertFunc, jsEvent, ui, view) {
                vm.alertMessage = ('Event Resized to make dayDelta ' + delta);
            };
            vm.addEvent = function (lecturer) {
                if (lecturer != undefined) {
                    vm.events.push({
                        title: lecturer.name
                        , start: new Date()
                        , end: new Date()
                        , className: [lecturer.name]
                        , draggable: true

                    });
                }
            };
            vm.addCourseEvent = function (course) {
                debugger;
                if (course != undefined) {
                    vm.events.push({
                        title: course.name
                        , start: new Date()
                        , end: new Date()
                        , className: [course.name]
                        , draggable: true

                    });
                }
            };

            function loadAll() {
                Lecturer.query(function (result) {
                    vm.lecturers = result;
                    vm.lecturers.forEach(function (lecturer, index) {
                        vm.addEvent(lecturer);
                    });
                });
                Course.query(function (result) {
                        vm.courses = result;
                        vm.courses.forEach(function (course, index) {
                            vm.addCourseEvent(course);
                        });
                    });
                }
            }
        })();
