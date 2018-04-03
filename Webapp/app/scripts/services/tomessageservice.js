'use strict';

/**
 * @ngdoc service
 * @name webappApp.toMessageService
 * @description 接收消息 service
 * # toMessageService
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('ToMessageService',['$http', 'CommonService', 'config', function ($http, CommonService, config) {
      var self = this;

      // 获取当前登录用户所有消息
      self.pageAllOfCurrentUser = function (params, callback) {
          var url = '/ToMessage/pageAllOfCurrentUser';
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
          var url = '/ToMessage/' + id;
          $http.get(url)
              .then(function success(response){
                  if (callback) {callback(response.data);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };

      self.delete = function (id, callback) {
          $http.delete('/ToMessage/' + id)
              .then(function success(response){
                  if (callback) {callback(response.status);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };


      // 回复
      self.reply = function (data, callback) {
          $http.post('/ToMessage/reply', data)
              .then(function success(response){
                  if (callback) {callback(response.status);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };

      // 将消息设置为已读
      self.setMessageToRead = function (id, callback) {
          $http.post('/ToMessage/setMessageToRead/' + id)
              .then(function success(response){
                  if (callback) {callback(response.status);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };

      // 获取当前用户的所有未读收件消息
      self.pageReceiveUnReadMessageOfCurrentUser = function (params, callback) {
          var url = '/ToMessage/pageReceiveUnReadToMessageOfCurrentUser';
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

      // 向当前用户所属市级管理部门发送消息
      self.sendMessageToManagementDepartmentOfCurrentUser = function (data, callback) {
          $http.post('/ToMessage/sendMessageToManagementDepartmentOfCurrentUser', data)
              .then(function success(response){
                  if (callback) {callback(response.status);}
              }, function error(response){
                  CommonService.httpError(response);
              });
      };

      // 批量删除
      self.batchDelete = function (data, callback) {
          $http.delete('/ToMessage/batchDelete/' + data)
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

          // 批量删除
          controller.batchDelete = function () {
              // 提示用户是否确定删除
              CommonService.warning(function (success, error) {
                  // 获取用户选中要删除的消息
                  var deleteData = CommonService.getCheckedElementsByListsAndKey(scope.data.content);
                  // 判断用户是否勾选消息
                  if (deleteData.length === 0) {
                      error('error', '请选择要删除的消息', '');
                  } else {
                      // 获取用户删除的消息的id数组
                      var params = [];
                      angular.forEach(deleteData, function(list) {
                          params.push(list.id);
                      });
                      // 批量删除消息
                      self.batchDelete(params, function (status) {
                          if (status === 204) {
                              // 提示用户删除成功
                              success();
                              // 刷新界面
                              controller.load();
                          } else {
                              error('error', '系统或网络异常', '');
                          }
                      });
                  }
              });
          };

          // 将消息设置为已读
          controller.setMessageToRead = function (data) {
              // 将消息设置为已读
              self.setMessageToRead(data.id);
          };
      };

      return{
          pageAllOfCurrentUser: self.pageAllOfCurrentUser,
          get: self.get,
          reply: self.reply,
          setMessageToRead: self.setMessageToRead,
          pageReceiveUnReadMessageOfCurrentUser: self.pageReceiveUnReadMessageOfCurrentUser,
          sendMessageToManagementDepartmentOfCurrentUser: self.sendMessageToManagementDepartmentOfCurrentUser,
          batchDelete: self.batchDelete,
          delete: self.delete,
          initController: self.initController
      };
  }]);
