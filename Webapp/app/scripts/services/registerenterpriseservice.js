'use strict';

/**
 * @ngdoc service
 * @name webappApp.RegisterEnterpriseService
 * @description 生产企业Service层
 * # RegisterEnterpriseService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('RegisterEnterpriseService', ['$http', 'CommonService', function($http, CommonService) {
        var all = function(callback) {
            // 调用$http获取模拟数据
            $http.get('/data/registerEnterprise/getRegisterEnterpriseArray.json').then(function successCallback(response) {
                callback(response.data);
            }, function errorCallback(response) {
                CommonService.httpError(response);
            });
        };

        return {
            all: all
        };
    }]);
