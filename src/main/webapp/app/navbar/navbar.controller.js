(function() {
    'use strict';

    angular
        .module('phonebookApp')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$state', 'Search'];

    function NavbarController ($state, Search) {
        var vm = this;

        vm.isNavbarCollapsed = false;
        vm.$state = $state;
        vm.Search = Search;

    }
})();
