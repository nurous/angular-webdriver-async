(function () {
    "use strict";

    n('hacks.webdriver', function (ns) {
        ns.events = [];

        function notify(response, status) {
            var config = response.config;
            ns.events.push(config.method + ":" + config.url + ":" + status);
        }

        ns.notifyAjaxFinished = function (response) {
            notify(response, "finished");
        };
        ns.notifyAjaxFailed = function (response) {
            notify(response, "failed");
        };
    });
    angular.module('ajaxFinished', []).config(['$httpProvider', function ($httpProvider) {

        var webDriverHacksInterceptor = function ($q, $timeout) {
            function success(response) {
                _.defer(function () {
                    hacks.webdriver.notifyAjaxFinished(response);
                });
                return response;
            }

            function error(response) {
                return $q.reject(response);
            }

            return function (promise) {
                return promise.then(success, error);
            };
        };

        $httpProvider.responseInterceptors.push(webDriverHacksInterceptor);
    }]);
}());

