(function() {
    'use strict';

    angular
        .module('phonebookApp')
        .config(compileServiceConfig);

    compileServiceConfig.$inject = ['$compileProvider'];

    function compileServiceConfig($compileProvider) {

        /* Sanitization filter for data to not be marked as unsafe */
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|tel):|data:image\//);
    }
})();
