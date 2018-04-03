'use strict';

/**
 * @ngdoc function是否为必填字段
 * @name webappApp.controller:RoleAddCtrl
 * @description     系统设置-角色管理新增功能
 * # RoleAddCtrl
 * @author zhangjiahao
 */
angular.module('webappApp')
    .controller('RoleAddCtrl', ['$scope', 'RoleRolefileService', 'CommonService',
        function($scope, RoleRolefileService, CommonService) {
        // 数据初始化
        var self = this;
        // 新增初始化
        self.addInit = function () {
            $scope.data = {
                name: '',
            };
        };
        self.addInit();

        self.update = function () {
            RoleRolefileService.saveRole($scope.data, function () {
                CommonService.success();
            });
        };
        $scope.update = self.update;
    }]);
