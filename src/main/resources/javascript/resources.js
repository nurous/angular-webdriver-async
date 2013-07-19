(function () {
    "use strict";
    angular.module('appResources', ['ngResource']).
        factory('Repositories', function ($resource) {
            return $resource('https://api.github.com/legacy/repos/search/:searchTerms',
                {searchTerms: '@searchTerms'});
        })
}());