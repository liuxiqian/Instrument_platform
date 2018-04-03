'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentAddCtrl
 * @description 添加部门controller
 * # DepartmentAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentAddCtrl', ['$scope',
        '$controller',
        function($scope, $controller) {


            //继承DepartmentEditCtrl控制器
            $controller('RegistrationCtrl', { $scope: $scope });

            //不会显示用户类型
            $scope.showDeapartmentType = false;
            $scope.data = { user: { department: { departmentType: {} } } };
        }
    ]);
