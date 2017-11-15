(function() {
    'use strict';

    angular
        .module('schedulingProjectApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('time', {
            parent: 'entity',
            url: '/time',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Times'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/time/times.html',
                    controller: 'TimeController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('time-detail', {
            parent: 'entity',
            url: '/time/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Time'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/time/time-detail.html',
                    controller: 'TimeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Time', function($stateParams, Time) {
                    return Time.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'time',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('time-detail.edit', {
            parent: 'time-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/time/time-dialog.html',
                    controller: 'TimeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Time', function(Time) {
                            return Time.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('time.new', {
            parent: 'time',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/time/time-dialog.html',
                    controller: 'TimeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                timePreference: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('time', null, { reload: 'time' });
                }, function() {
                    $state.go('time');
                });
            }]
        })
        .state('time.edit', {
            parent: 'time',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/time/time-dialog.html',
                    controller: 'TimeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Time', function(Time) {
                            return Time.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('time', null, { reload: 'time' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('time.delete', {
            parent: 'time',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/time/time-delete-dialog.html',
                    controller: 'TimeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Time', function(Time) {
                            return Time.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('time', null, { reload: 'time' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
