'use strict';

/**
 * 技术机构controller
 * @ngdoc function
 * @name webappApp.controller:DepartmentTechnicalinstitutionIndexCtrl
 * @description
 * # DepartmentTechnicalinstitutionIndexCtrl
 * Controller of the webappApp
 * gaoliming
 */
angular.module('webappApp')
    .controller('DepartmentTechnicalinstitutionIndexCtrl', ['$scope', '$controller', function($scope, $controller) {
    	//继承DepartmentIndexCtrl控制器
        $controller('DepartmentIndexCtrl', { $scope: $scope });

        //设置是否显示注册人的相关信息
        $scope.showInfo = true;
    }]);
