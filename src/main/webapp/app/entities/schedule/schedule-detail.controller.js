(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('ScheduleDetailController', ScheduleDetailController);

    ScheduleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Schedule', 'Lecturer', 'Course'];

    function ScheduleDetailController($scope, $rootScope, $stateParams, previousState, entity, Schedule, Lecturer, Course) {
        var vm = this;

        vm.schedule = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schedulingProjectApp:scheduleUpdate', function(event, result) {
            vm.schedule = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
