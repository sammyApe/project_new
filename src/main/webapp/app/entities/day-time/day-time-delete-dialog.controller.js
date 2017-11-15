(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('DayTimeDeleteController',DayTimeDeleteController);

    DayTimeDeleteController.$inject = ['$uibModalInstance', 'entity', 'DayTime'];

    function DayTimeDeleteController($uibModalInstance, entity, DayTime) {
        var vm = this;

        vm.dayTime = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DayTime.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
