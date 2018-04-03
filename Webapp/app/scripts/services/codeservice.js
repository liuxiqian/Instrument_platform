'use strict';

/**
 * @ngdoc service
 * @name webappApp.codeService
 * @description 代码service
 * # codeService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('codeService', ['$http', function ($http, CommonService) {
        var self = this;
        this.getCode = function (callback) {
            // 利用$http获取代码
            $http.get('/data/code/getCodeArray.json').then(function successCallback(response) {
                callback(response.data);
            },function errorCallback(response) {
                CommonService.httpError(response);
            });
        };
        return{
            getCode:self.getCode
        };

    }]);
