'use strict';

/**
 * @ngdoc service
 * @name webappApp.OptionalCheckdetailService
 * @description
 * # OptionalCheckdetailService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('OptionalCheckdetailService', ['$http', 'CommonService', function ($http, CommonService) {
    var getAll = function (callback) {
          // 调用$http获取模拟数据
          $http.get('/data/checkdetail/getAllCheckdetailArray.json').then(function successCallback(response) {
              callback(response.data);
          },function errorCallback(response) {
              CommonService.httpError(response);
          });
      };

      return{
          getAll:getAll
      };
  }]);
