'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentManagementAddCtrl
 * @description  用户管理 管理部门 -》 新增
 * # DepartmentManagementAddCtrl
 * Controller of the webappApp
 * @author  panjie
 */
angular.module('webappApp')
    .controller('DepartmentManagementAddCtrl', ['$scope', '$controller', '$stateParams', 'departmentTypeService', function($scope, $controller, $stateParams, departmentTypeService) {
    	var self = this;
        //继承DepartmentEditCtrl控制器
        $controller('DepartmentAddCtrl', { $scope: $scope });

        //获取所有的部门类型
        self.init = function() {
            //获取所有的部门类型
            departmentTypeService.getDepartmentTypeOfManagement(function(data) {
                $scope.data.user.department.departmentType = data;
            });
        };

        self.init();
    }]);
