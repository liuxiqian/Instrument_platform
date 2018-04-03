'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:UserUserfileAddCtrl
 * @description 用户管理 addController
 * # UserUserfileAddcontrollerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('UserUserfileAddCtrl', ['$scope', '$controller', '$stateParams', 'departmentService', function($scope, $controller, $stateParams, departmentService) {
        var self = this;

        //继承DepartmentEditCtrl控制器
        $controller('RegistrationCtrl', { $scope: $scope });

        // 获取跳转的路由参数
        self.departmentId = $stateParams.departmentId;
        $scope.data = {};

        //获取编辑用户的信息
        self.getOneDepartmentOfId = function() {
            departmentService.getOneDepartmentOfId(self.departmentId, function(department) {
                $scope.data.user = {username: '', password: '', department:department};
            });
        };

        self.getOneDepartmentOfId();
    }]);
