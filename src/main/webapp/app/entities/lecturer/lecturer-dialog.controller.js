(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('LecturerDialogController', LecturerDialogController);

    LecturerDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Lecturer', 'DayTime', 'Course', 'Session'];

    function LecturerDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Lecturer, DayTime, Course, Session) {
        var vm = this;

        vm.lecturer = entity;
        vm.clear = clear;
        vm.save = save;
        vm.daytimes = DayTime.query();
        vm.courses = Course.query();
        vm.getLecturerSessions=getLecturerSessions;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.lecturer.id !== null) {
                Lecturer.update(vm.lecturer, onSaveSuccess, onSaveError);
            } else {
            debugger;
                Lecturer.save(vm.lecturer, onSaveSuccess, onSaveError);
            }
            debugger;
        }

        function onSaveSuccess (result) {
            $scope.$emit('schedulingProjectApp:lecturerUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

       function getLecturerSessions(){
        vm.sessions=[];
         angular.forEach(vm.lecturer.courseLists, function(course,key){
            debugger;
                if(course.sectionLists != null){
                    angular.forEach(course.sectionLists, function(session,key){
                    vm.sessions.push(session);

                    });
               }
         });

         vm.lecturer.sessionList = vm.sessions;
        }

        getLecturerSessions();



    }
})();
