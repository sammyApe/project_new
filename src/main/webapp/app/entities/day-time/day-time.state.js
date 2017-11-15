(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('day-time', {
            parent: 'entity',
            url: '/day-time',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DayTimes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/day-time/day-times.html',
                    controller: 'DayTimeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('day-time-detail', {
            parent: 'entity',
            url: '/day-time/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'DayTime'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/day-time/day-time-detail.html',
                    controller: 'DayTimeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'DayTime', function($stateParams, DayTime) {
                    return DayTime.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'day-time',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('day-time-detail.edit', {
            parent: 'day-time-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/day-time/day-time-dialog.html',
                    controller: 'DayTimeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DayTime', function(DayTime) {
                            return DayTime.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('day-time.new', {
            parent: 'day-time',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/day-time/day-time-dialog.html',
                    controller: 'DayTimeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dayPreference: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('day-time', null, { reload: 'day-time' });
                }, function() {
                    $state.go('day-time');
                });
            }]
        })
        .state('day-time.edit', {
            parent: 'day-time',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/day-time/day-time-dialog.html',
                    controller: 'DayTimeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DayTime', function(DayTime) {
                            return DayTime.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('day-time', null, { reload: 'day-time' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('day-time.delete', {
            parent: 'day-time',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/day-time/day-time-delete-dialog.html',
                    controller: 'DayTimeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DayTime', function(DayTime) {
                            return DayTime.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('day-time', null, { reload: 'day-time' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
