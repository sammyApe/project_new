(function() {
    'use strict';
    angular
        .module('schedulingProjectApp')
        .factory('DayTime', DayTime);

    DayTime.$inject = ['$resource'];

    function DayTime ($resource) {
        var resourceUrl =  'api/day-times/:id';

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
