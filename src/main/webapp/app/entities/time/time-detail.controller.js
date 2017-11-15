(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('TimeDetailController', TimeDetailController);

    TimeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Time', 'DayTime'];

    function TimeDetailController($scope, $rootScope, $stateParams, previousState, entity, Time, DayTime) {
        var vm = this;

        vm.time = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schedulingProjectApp:timeUpdate', function(event, result) {
            vm.time = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
