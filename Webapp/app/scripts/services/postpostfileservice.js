'use strict';

/**
 * @ngdoc service
 * @name webappApp.PostPostfileService
 * @description
 * # PostPostfileService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('PostPostfileService', ['$http', 'CommonService', function($http, CommonService) {
    var getAll = function (callback) {
          // 调用$http获取模拟数据
          $http.get('/data/post/postfileIndexArray.json').then(function successCallback(response) {
              callback(response.data);
          },function errorCallback(response) {
              CommonService.httpError(response);
          });
      };

      return{
          getAll:getAll
      };
  }]);
