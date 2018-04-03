'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentEnterpriseAddCtrl
 * @description
 * # DepartmentEnterpriseAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentEnterpriseAddCtrl', ['$scope', '$controller', '$stateParams', 'departmentTypeService', function($scope, $controller, $stateParams, departmentTypeService) {
        var self = this;
        //继承DepartmentEditCtrl控制器
        $controller('DepartmentAddCtrl', { $scope: $scope });

        self.init = function() {
            //获取器具用户的部门类型
            departmentTypeService.getDepartmentTypeOfEnterprise(function(data) {
                $scope.data.user.department.departmentType = data;
            });
        };
        self.init();

    }]);
