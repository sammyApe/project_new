(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('DayTimeController', DayTimeController);

    DayTimeController.$inject = ['$scope', '$state', 'DayTime'];

    function DayTimeController ($scope, $state, DayTime) {
        var vm = this;

        vm.dayTimes = [];

        loadAll();

        function loadAll() {
            DayTime.query(function(result) {
                vm.dayTimes = result;
            });
        }
    }
})();
