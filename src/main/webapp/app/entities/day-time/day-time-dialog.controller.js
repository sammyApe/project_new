(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('DayTimeDialogController', DayTimeDialogController);

    DayTimeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DayTime', 'Lecturer', 'Time'];

    function DayTimeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DayTime, Lecturer, Time) {
        var vm = this;

        vm.dayTime = entity;
        vm.clear = clear;
        vm.save = save;
        vm.lecturers = Lecturer.query();
        vm.times = Time.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.dayTime.id !== null) {
                DayTime.update(vm.dayTime, onSaveSuccess, onSaveError);
            } else {
                DayTime.save(vm.dayTime, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schedulingProjectApp:dayTimeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
