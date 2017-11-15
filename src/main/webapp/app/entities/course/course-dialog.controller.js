(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('CourseDialogController', CourseDialogController);

    CourseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Course', 'Session'];

    function CourseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Course, Session) {
        var vm = this;
        vm.sessionEntity = {name: null}


        vm.course = entity;
        vm.clear = clear;
        vm.save = save;
        //vm.sessions = Session.query();
        vm.saveSession=saveSession;


        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }


        function save () {
            vm.isSaving = true;

            if (vm.course.id !== null) {
                Course.update(vm.course, onSaveSuccess, onSaveError);
            } else {
            debugger;
                Course.save(vm.course, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('schedulingProjectApp:courseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

      function saveSession(){
           debugger;

           vm.sessionEntity.name=vm.course.name +"_"+vm.session;
           vm.course.sectionLists.push(vm.sessionEntity);
           vm.session="";

          console.log("saving session");

        }

    }
})();
