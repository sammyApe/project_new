(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('SessionDetailController', SessionDetailController);

    SessionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Session'];

    function SessionDetailController($scope, $rootScope, $stateParams, previousState, entity, Session) {
        var vm = this;

        vm.session = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('schedulingProjectApp:sessionUpdate', function(event, result) {
            vm.session = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
