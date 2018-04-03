'use strict';

/**
 * @ngdoc service
 * @name webappApp.FromMessageService
 * @description
 * # FromMessageService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('FromMessageService', ['$http', 'CommonService', 'config', function ($http, CommonService, config) {
      var self = this;

      // 获取当前登录用户所有消息
      self.pageAllOfCurrentUser = function (params, callback) {
          var url = '/FromMessage/pageAllOfCurrentUser';
          var queryParams = CommonService.querySerialize(params);
          $http.get(url + '?' + queryParams)
              .then(function success (response) {
                  if (callback) {
                      callback(response.data);
                  }
              }, function error(response) {
                  CommonService.httpError(response);
              });
      };

      self.get = function (id, callback) {
          var url = '/FromMessage/' + id;
          $http.get(url)
              .then(function success(response){
                  if (callback) {callback(response.data);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };

      // 批量删除
      self.batchDelete = function (data, callback) {
          $http.delete('/FromMessage/batchDelete/' + data)
              .then(function success(response){
                  if (callback) {callback(response.status);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };

      /**
       * 初始化控制器
       * 1.初始化分页参数
       * 2.插叙分页信息
       * @param controller
       * @param scope
       * @param stateParams
       * @author chuhang
       */
      self.initController = function (controller, scope) {
          // 获取ui-route传入的参考
          controller.initScopeParams = function () {
              return {
                  page: 0,
                  size: config.size
              };
          };

          // 获取查询参数
          controller.generateQueryParams = function () {
              // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
              return {
                  page: scope.params.page,
                  size: scope.params.size
              };
          };

          // 加载数据
          scope.reload = controller.reload = controller.load = function () {
              var params = controller.generateQueryParams();
              self.pageAllOfCurrentUser(params, function(data) {
                  scope.data = data;
              });
          };
      };

      return{
          pageAllOfCurrentUser: self.pageAllOfCurrentUser,
          get: self.get,
          batchDelete: self.batchDelete,
          initController: self.initController
      };
  }]);
