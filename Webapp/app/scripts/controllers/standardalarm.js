'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardalarmCtrl
 * @description
 * # StandardalarmCtrl
 * Controller of the webappApp
 * zhuchenshu
 * 标准器报警
 */
angular.module('webappApp')
  .controller('StandardAlarmCtrl', function ($scope, StandardAlarmService) {
    	var self = this;

      self.init = function() {
        var initialPage = 0; // 初始界面
        self.reloadByPage(initialPage);
      };

      /**
       * 请求数据
       * @param  {int} page 当前页
       * @return 
       */
      self.reloadByPage = function(page) {
          // 初始化分页参数，并发起请求
          var size = 10;    // 初始分页大小
          var param = {page:page, size: size};
          StandardAlarmService.pageAllAlarmStandardDevice(param, function(data) {
              $scope.data = data;
          });
      };

      $scope.reloadByPage = self.reloadByPage;
    	self.init();
  });
