(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('DayTimeDetailController', DayTimeDetailController);

    DayTimeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DayTime', 'Lecturer', 'Time'];

    function DayTimeDetailController($scope, $rootScope, $stateParams, previousState, entity, DayTime, Lecturer, Time) {
        var vm = this;

        vm.dayTime = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schedulingProjectApp:dayTimeUpdate', function(event, result) {
            vm.dayTime = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
