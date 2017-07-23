(function() {
    'use strict';

    angular
        .module('phonebookApp')
        .controller('ContactsController', ContactsController);

    ContactsController.$inject = ['$scope', 'Contacts', 'PAGE_SIZE', 'Search'];

    function ContactsController ($scope, Contacts, PAGE_SIZE, Search) {
        var vm = this;

        vm.contacts = [];
        vm.loadPage = loadPage;
        vm.page = 0;
        vm.totalItems = 0;
        vm.links = {
            last: 0
        };
        vm.predicate = 'name';
        vm.reset = reset;
        vm.Search = Search;

        $scope.$watch(function() {
            return vm.Search.q;
        }, function() {
            vm.reset();
        });

        function loadAll () {
            Contacts.query({
                page: vm.page,
                size: PAGE_SIZE,
                sort: sort(),
                q: !Search.q ? null : Search.q
            }, onSuccess, onError);
            function sort() {
                var result = [vm.predicate + ',' + 'asc'];
                if (vm.predicate !== 'id') {
                    result.push('id');
                }
                return result;
            }

            function onSuccess(data, headers) {
                vm.totalItems = headers('X-Total-Count');
                for (var i = 0; i < data.length; i++) {
                    vm.contacts.push(data[i]);
                }
            }

            function onError(error) {

            }
        }

        function reset () {
            vm.page = 0;
            vm.contacts = [];
            loadAll();
        }

        function loadPage(page) {
            vm.page = page;
            loadAll();
        }

    }
})();
