(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('SessionDialogController', SessionDialogController);

    SessionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Session'];

    function SessionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Session) {
        var vm = this;

        vm.session = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.session.id !== null) {
                Session.update(vm.session, onSaveSuccess, onSaveError);
            } else {
                Session.save(vm.session, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schedulingProjectApp:sessionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
