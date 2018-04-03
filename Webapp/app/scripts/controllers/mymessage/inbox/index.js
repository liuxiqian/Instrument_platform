'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MymessageInboxIndexCtrl
 * @description
 * # MymessageInboxIndexCtrl
 * 收件箱
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('MyMessageInboxIndexCtrl', ['$scope', '$stateParams', '$state', 'config', 'ToMessageService', 'CommonService', function ($scope, $stateParams, $state, config, ToMessageService, CommonService) {
      var self = this;

      // 初始化分页参数
      self.init = function() {
          CommonService.initControllerPage(self, $scope);
          ToMessageService.initController(self, $scope, $stateParams);
          $scope.params = self.initScopeParams();
          self.load();
      };

      self.load = self.reload = self.reloadInbox = function () {
          var params = self.generateQueryParams();
          ToMessageService.pageAllOfCurrentUser(params, function(data) {
              $scope.data = data;
          });
      };

      // 执行初始化信息
      self.init();

      // 统一暴露
      $scope.setMessageToRead = self.setMessageToRead;
      $scope.batchDelete = self.batchDelete;
  }]);
