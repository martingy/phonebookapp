(function() {
    'use strict';
    angular
        .module('phonebookApp')
        .factory('Contacts', Contacts)

    Contacts.$inject = ['$resource'];

    function Contacts ($resource) {
        var resourceUrl =  'api/contacts';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
