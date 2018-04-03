'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MymessageHasbeensentDetailCtrl
 * @description
 * # MymessageHasbeensentDetailCtrl
 * 已发送 详情
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MyMessageSentDetailCtrl', ['$scope', '$stateParams', 'FromMessageService', function ($scope, $stateParams, FromMessageService) {
      var self = this;
      self.id = $stateParams.id;

      // 根据消息id获取消息
      self.get = function () {
          FromMessageService.get(self.id, function (data) {
              $scope.data = data;
          });
      };

      // 获取消息
      self.get();

  }]);
