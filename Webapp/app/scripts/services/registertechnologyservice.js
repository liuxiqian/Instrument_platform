'use strict';

/**
 * @ngdoc service
 * @name webappApp.RegisterTechnologyService
 * @description 技术机构Service层
 * # RegisterTechnologyService
 * Service in the webappApp.
 */
angular.module('webappApp')
    .service('RegisterTechnologyService', ['$http', 'CommonService', function($http, CommonService) {
        var all = function(callback) {
            // 调用$http获取模拟数据
            $http.get('/data/registerTechnology/getRegisterTechnologyArray.json').then(function successCallback(response) {
                callback(response.data);
            }, function errorCallback(response) {
                CommonService.httpError(response);
            });
        };

        return {
            all: all
        };
    }]);
