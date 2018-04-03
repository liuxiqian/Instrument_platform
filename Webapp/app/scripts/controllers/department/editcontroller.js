'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentEditcontrollerCtrl
 * @description
 * # DepartmentEditcontrollerCtrl
 * 用户管理 编辑 楚航
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('DepartmentEditCtrl', ['$scope', '$stateParams', 'CommonService', 'departmentService', function($scope, $stateParams, CommonService, departmentService) {

        var self = this;

        // 获取跳转的路由参数
        self.departmentId = $stateParams.id;
        $scope.data = { user: {} };

        //获取编辑用户的信息
        self.getOneDepartmentOfId = function() {
            departmentService.getOneDepartmentOfId(self.departmentId, function(data) {
                $scope.data.user.department = data;
            });
        };

        self.getOneDepartmentOfId();

        // 监听data.department.departmentType 当data.department.departmentType每次改变时, 都会触发显示或隐藏不同的字段信息
        $scope.$watch('data.user.department.departmentType', function(newValue) {
            if (newValue) {
                switch (newValue.pinyin) {
                    case "guanlibumen":
                        $scope.departmentTypes = false;
                        break;
                    case "qijuyonghu":
                        $scope.departmentTypes = true;
                        break;
                    case "shengchanqiye":
                        $scope.departmentTypes = true;
                        break;
                    case "jishujigou":
                        $scope.departmentTypes = true;
                        break;
                    default:
                        $scope.departmentTypes = false;
                }
            }

        }, true);

        //手机号码验证
        $scope.regex = "^0?1[0-9]{10}$";

        self.update = function() {
            departmentService.update($scope.data.user.department, function() {
                CommonService.success();
            });
        };

        //　是否显示全部区域
        $scope.all = false;
        $scope.local = true;

        //统一暴露方法
        $scope.register = self.update;
        $scope.showUserMessage = self.showUserMessage;
    }]);
