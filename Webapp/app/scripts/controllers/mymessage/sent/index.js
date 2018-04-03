'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MyMessageHasBeenSentIndexCtrl
 * @description
 * # MyMessageHasBeenSentIndexCtrl
 * 已发送
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MyMessageSentIndexCtrl', ['$scope', '$stateParams', '$state', 'FromMessageService', 'config', 'CommonService', function ($scope, $stateParams, $state, FromMessageService, config, CommonService) {
      var self = this;

      // 初始化分页参数
      self.init = function() {
          CommonService.initControllerPage(self, $scope);
          FromMessageService.initController(self, $scope, $stateParams);
          $scope.params = self.initScopeParams();
          self.load();
      };

      // 批量删除
      self.batchDelete = function () {
          // 提示用户是否确定删除
          CommonService.warning(function (success, error) {
              // 获取用户选中要删除的消息
              self.deleteData = CommonService.getCheckedElementsByListsAndKey($scope.data.content);
              // 判断用户是否勾选消息
              if (self.deleteData.length === 0) {
                  error('error', '请选择要删除的消息', '');
              } else {
                  // 获取用户删除的消息的id数组
                  var params = [];
                  angular.forEach(self.deleteData, function(list) {
                      params.push(list.id);
                  });
                  // 批量删除消息
                  FromMessageService.batchDelete(params, function (status) {
                      if (status === 204) {
                          // 提示用户删除成功
                          success();
                          // 刷新界面
                          $state.reload();
                      } else {
                          error('error', '系统或网络异常', '');
                      }
                  });
              }
          });

      };

      // 统一暴露
      $scope.batchDelete = self.batchDelete;

      // 执行初始化信息
      self.init();
  }]);
