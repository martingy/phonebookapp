(function() {
    'use strict';

    angular
        .module('phonebookApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('contacts', {
            parent: 'app',
            url: '/',
            data: {
                pageTitle: 'phonebookApp'
            },
            views: {
                'content@': {
                    templateUrl: 'app/contacts/contacts.html',
                    controller: 'ContactsController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
