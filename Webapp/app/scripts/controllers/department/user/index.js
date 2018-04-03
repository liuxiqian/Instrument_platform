'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentUserIndexCtrl
 * @description 
 * 用户管理 -> 登录用户管理
 * # DepartmentUserIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentUserIndexCtrl', function($controller,
        $scope,
        UserServer,
        $stateParams,
        CommonService,
        departmentService) {
        var self = this;
        $scope.init = function() {};
        $controller('UserUserfileIndexCtrl', { $scope: $scope });

        self.init = function() {
            var department = { id: $stateParams.departmentId };
            UserServer.getAllByDepartmentId(department.id, function(data) {
                $scope.data = data;
            });

            departmentService.getById(department.id, function(data) {
                $scope.department = data;
            });
        };


        self.init();
        $scope.back = CommonService.back;
        $scope.showBack = CommonService.showBack;
    });
