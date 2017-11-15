(function() {
    'use strict';
    angular
        .module('schedulingProjectApp')
        .factory('Session', Session);

    Session.$inject = ['$resource'];

    function Session ($resource) {
        var resourceUrl =  'api/sessions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
