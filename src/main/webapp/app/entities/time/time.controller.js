(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('TimeController', TimeController);

    TimeController.$inject = ['$scope', '$state', 'Time'];

    function TimeController ($scope, $state, Time) {
        var vm = this;

        vm.times = [];

        loadAll();

        function loadAll() {
            Time.query(function(result) {
                vm.times = result;
            });
        }
    }
})();
