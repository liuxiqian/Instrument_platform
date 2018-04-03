'use strict';

/**
 * 器具用户controller
 * @ngdoc function
 * @name webappApp.controller:DepartmentInstrumentuserIndexCtrl
 * @description
 * # DepartmentInstrumentuserIndexCtrl
 * Controller of the webappApp
 * gaoliming
 */
angular.module('webappApp')
    .controller('DepartmentInstrumentuserIndexCtrl', ['$scope', '$controller', function($scope, $controller) {
        //继承DepartmentIndexCtrl控制器
        $controller('DepartmentIndexCtrl', { $scope: $scope });

        //设置是否显示注册人的相关信息
        $scope.showInfo = true;
    }]);
