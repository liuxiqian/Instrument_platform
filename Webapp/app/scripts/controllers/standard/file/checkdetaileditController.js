'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileCheckdetaileditCtrl
 * @description
 * # 标准检定信息 编辑 控制器
 * @Author poshichao
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileCheckdetailEditCtrl', function($scope, StandardDeviceCheckDetailService, $stateParams, CommonService, $http) {
    	var self = this;

    	// 初始化编辑界面
    	self.init = function() {
    		var checkDetailId = $stateParams.id;
    		var url = '/StandardDeviceCheckDetail/' + checkDetailId;
    		$http.get(url)
                .then(function success(response) {
                    // 将获取的数据传递给V层,进行绑定
                    $scope.data = response.data;
                }, function error(response) {
                    console.log('error ' + url, response);
                });
    	};

    	// 更新
    	self.update = function (callback) {
    		StandardDeviceCheckDetailService.update($scope.data, callback);
    	};

    	// 保存并关闭
        self.saveAndClose = function () {
            self.update(function () {
                CommonService.success();
            });
        };

    	self.init();

    	$scope.saveAndClose = self.saveAndClose;
    });
