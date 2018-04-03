'use strict';

/**
 * 管理部门controller
 * @ngdoc function
 * @name webappApp.controller:DepartmentManagementIndexCtrl
 * @description
 * # DepartmentManagementIndexCtrl
 * Controller of the webappApp
 * gaoliming
 */
angular.module('webappApp')
    .controller('DepartmentManagementIndexCtrl', ['$scope', '$controller', function($scope, $controller) {
    
    	//继承DepartmentIndexCtrl控制器
        $controller('DepartmentIndexCtrl', { $scope: $scope });

        //设置是否显示注册人的相关信息
        $scope.showInfo = false;   
    }]);
