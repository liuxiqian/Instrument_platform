'use strict';

/**
 * @ngdoc service
 * @name webappApp.registerMeasureDeviceservice
 * @description 用户管理-器具用户service
 * # registerMeasureDeviceservice
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('registerMeasureDeviceservice', ['$http', 'CommonService', function ($http, CommonService) {
      var getAll = function (callback) {
          // 调用$http获取模拟数据
          $http.get('/data/registerMeasureDevice/getAllMeasureDeviceArray.json').then(function successCallback(response) {
              callback(response.data);
          },function errorCallback(response) {
              CommonService.httpError(response);
          });
      };

      return{
          getAll:getAll
      };
  }]);
