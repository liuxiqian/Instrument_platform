'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardwarnctrlCtrl
 * @description
 * # StandardwarnctrlCtrl
 * Controller of the webappApp
 * 标准器预警
 * zhuchenshu
 */
angular.module('webappApp')
  .controller('StandardWarnCtrl', function ($scope, StandardWarnService) {
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
		  StandardWarnService.pageAllWarnStandardDevice(param, function(data) {
		      $scope.data = data;
		  });
		};

		$scope.reloadByPage = self.reloadByPage;
		self.init();
  });
