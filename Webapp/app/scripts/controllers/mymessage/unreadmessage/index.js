'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MymessageUnreadmessageIndexCtrl
 * @description
 * # MymessageUnreadmessageIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MyMessageUnReadMessageIndexCtrl', ['$scope', '$controller', '$stateParams', 'config', 'ToMessageService', 'CommonService', function ($scope, $controller, $stateParams, config, ToMessageService, CommonService) {
      var self = this;

      // 初始化分页参数
      self.init = function() {
          CommonService.initControllerPage(self, $scope);
          ToMessageService.initController(self, $scope, $stateParams);
          $scope.params = self.initScopeParams();
          self.load();
      };

      self.load = self.reload = self.reloadUnReadMessage = function () {
          var params = self.generateQueryParams();
          ToMessageService.pageReceiveUnReadMessageOfCurrentUser(params, function(data) {
              $scope.data = data;
          });
      };

      // 执行初始化信息
      self.init();

      // 统一暴露
      $scope.setMessageToRead = self.setMessageToRead;
      $scope.batchDelete = self.batchDelete;
  }]);
