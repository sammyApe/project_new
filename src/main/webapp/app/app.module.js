(function() {
    'use strict';

    angular
        .module('schedulingProjectApp', [
            'ngStorage',
            'ngResource',
            'ngCookies',
            'ngAria',
            'ngCacheBuster',
            'ngFileUpload',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'ui.calendar',
            'infinite-scroll',
            'ngDraggable',
            //'ngDragDrop',
            // jhipster-needle-angularjs-add-module JHipster will add new module here
            'angular-loading-bar'


        ])
        .run(run);

    run.$inject = ['stateHandler'];

    function run(stateHandler) {
        stateHandler.initialize();
    }
})();
