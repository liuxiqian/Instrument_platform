'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MymessageInboxDetailCtrl
 * @description
 * # MymessageInboxDetailCtrl
 * 收件箱 详情
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MyMessageInboxDetailCtrl', ['$scope', '$state', '$stateParams', 'CommonService', 'ToMessageService', function ($scope, $state, $stateParams, CommonService, ToMessageService) {
      var self = this;
      self.id = $stateParams.id;

      // 删除
      self.delete = function (message) {
          // 提示用户是否确定删除
          CommonService.warning(function (success, error) {
              // 请求后台，删除数据
              ToMessageService.delete(message.id, function (status) {
                  if (204 === status) {
                      // 隐藏该记录
                      message.hide = true;
                      // 提示用户删除成功
                      success();
                      // 跳转至收件箱菜单
                      $state.go('myMessage.inbox');
                  } else {
                      // 中间表无关联删除的异常，如删除失败，应是网络问题
                      error('error', '系统或网络异常', '');
                  }
              });
          });
      };

      // 获取消息
      self.get = function () {
          ToMessageService.get(self.id, function (data) {
              $scope.data = data;
          });
      };

      // 统一暴露
      $scope.delete = self.delete;
      self.get();
  }]);
