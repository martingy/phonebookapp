(function() {
    'use strict';
    angular
        .module('phonebookApp')
        .factory('Search', Search)

    Search.$inject = [];

    function Search () {
        return { q: '' };
    }
})();
