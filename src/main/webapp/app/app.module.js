(function() {
    'use strict';

    angular
        .module('phonebookApp', [
            'ngResource',
            'ui.bootstrap',
            'ui.bootstrap.datetimepicker',
            'ui.router',
            'infinite-scroll',
            'angular-loading-bar'
        ])
        .run(run);

    run.$inject = ['stateHandler', '$state', '$rootScope', 'PAGE_SIZE'];

    function run(stateHandler, $state, $rootScope, PAGE_SIZE) {
        stateHandler.initialize();
        $rootScope.$state = $state;
        $rootScope.PAGE_SIZE = PAGE_SIZE
    }
})();
