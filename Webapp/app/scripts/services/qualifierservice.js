'use strict';

/**
 * @ngdoc service
 * @name webappApp.QualifierService
 * @description
 * # QualifierService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('QualifierService', ['$http', 'CommonService', function ($http, CommonService) {
        var self = this;

        self.getAllOfCurrentUser = function (callback) {
            var getAllOfCurrentUserUrl = '/Qualifier/getAllByCurrentLoginUserDepartment';

            $http.get(getAllOfCurrentUserUrl)
                .then(function success(response) {
                    callback(response.data);
                }, function error(response) {
                    CommonService.httpError(response);
                });
        };

        return {
            getAllOfCurrentUser: self.getAllOfCurrentUser
        };
    }]);
