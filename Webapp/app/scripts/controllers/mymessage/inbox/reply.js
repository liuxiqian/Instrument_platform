'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MymessageInboxReplyCtrl
 * @description
 * # MymessageInboxReplyCtrl
 * 消息回复模态框
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MyMessageInboxReplyCtrl', ['$scope', '$stateParams', '$state', 'ToMessageService', 'CommonService', function ($scope, $stateParams, $state, ToMessageService, CommonService) {
      var self = this;

      //初始化信息
      self.id = $stateParams.id;
      $scope.message = {};

      // 根据消息id获取消息
      self.init = function (id) {
          ToMessageService.get(id, function (data) {
              $scope.data = data;
          });
      };

      // 保存
      self.reply = function () {
          // 设定消息的发送部门
          $scope.message.toDepartment = $scope.data.fromDepartment;
          ToMessageService.reply($scope.message, function () {
              // 提示用户操作成功，并返回到收件箱列表页面
              CommonService.success(undefined, undefined, function () {
                  $state.go('myMessage.inbox');
              });
          });
      };

      // 统一暴露
      $scope.reply = self.reply;

      // 获取消息
      self.init(self.id);
  }]);
