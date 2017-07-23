(function() {
    'use strict';

    angular
        .module('phonebookApp')
        .config(httpConfig);

    httpConfig.$inject = ['$urlRouterProvider', '$httpProvider', '$urlMatcherFactoryProvider'];

    function httpConfig($urlRouterProvider, $httpProvider, $urlMatcherFactoryProvider) {

        $urlRouterProvider.otherwise('/');

    }
})();
