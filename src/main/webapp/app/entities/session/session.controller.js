(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('SessionController', SessionController);

    SessionController.$inject = ['$scope', '$state', 'Session'];

    function SessionController ($scope, $state, Session) {
        var vm = this;

        vm.sessions = [];

        loadAll();

        function loadAll() {
            Session.query(function(result) {
                vm.sessions = result;
            });
        }
    }
})();
