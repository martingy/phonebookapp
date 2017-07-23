(function () {
    'use strict';

    angular
        .module('phonebookApp')
        .config(pagerConfig);

    pagerConfig.$inject = ['uibPagerConfig'];

    function pagerConfig(uibPagerConfig) {
        uibPagerConfig.itemsPerPage = 20;
        uibPagerConfig.previousText = '«';
        uibPagerConfig.nextText = '»';
    }
})();
