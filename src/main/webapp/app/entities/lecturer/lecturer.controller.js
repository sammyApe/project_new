(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .controller('LecturerController', LecturerController);

    LecturerController.$inject = ['$scope', '$state', 'Lecturer'];

    function LecturerController ($scope, $state, Lecturer) {
        var vm = this;

        vm.lecturers = [];

        loadAll();

        function loadAll() {
            Lecturer.query(function(result) {
                vm.lecturers = result;
                checkSectionListWithLoad(vm.lecturers)
            });
        }

        function checkSectionListWithLoad(lecturers)
        {
        angular.forEach(vm.lecturers, function(lecturer,key){
            if(lecturer.teachingLoad < lecturer.sessionLists.length){
                alert("Faculty member " + lecturer.name + " has more sessions than allowed");
            }
        });
        }

    }
})();
