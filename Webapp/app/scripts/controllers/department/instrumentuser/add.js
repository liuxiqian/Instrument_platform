'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentInstrumentuserAddCtrl
 * @description
 * # DepartmentInstrumentuserAddCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentInstrumentUserAddCtrl', ['$scope', '$controller', '$stateParams', 'departmentTypeService', function($scope, $controller, $stateParams, departmentTypeService) {
        var self = this;
        //继承DepartmentEditCtrl控制器
        $controller('DepartmentAddCtrl', { $scope: $scope });
        self.init = function() {
            //获取器具用户的部门类型
            departmentTypeService.getDepartmentTypeOfInstrumentuser(function(data) {
                $scope.data.user.department.departmentType = data;
            });
        };
        self.init();

    }]);
