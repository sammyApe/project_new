(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('LecturerDetailController', LecturerDetailController);

    LecturerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Lecturer', 'DayTime', 'Course', 'Session'];

    function LecturerDetailController($scope, $rootScope, $stateParams, previousState, entity, Lecturer, DayTime, Course, Session) {
        var vm = this;

        vm.lecturer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schedulingProjectApp:lecturerUpdate', function(event, result) {
            vm.lecturer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
