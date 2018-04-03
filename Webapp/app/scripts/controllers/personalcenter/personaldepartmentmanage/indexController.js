'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:PersonalcenterPersonalinfomanageIndexCtrl
 * @description 个人中心-部门信息--C层
 * # PersonalcenterPersonaldepartmentmanageIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('PersonalcenterPersonalDepartmentManageIndexCtrl', ['UserServer', '$scope', 'departmentService', '$state', 'CommonService', function (UserServer, $scope, departmentService, $state, CommonService) {
        var self = this;

        // 定义获取数据方法
        var showData = function () {
            // 获取后台数据
            UserServer.getCurrentLoginUser(function (data) {
                $scope.data = data.department;
            });
        };

        // 执行获取数据
        showData();

        //手机号码验证
        $scope.regex = "^0?1[0-9]{10}$";
        $scope.regextel = "([0-9]{3,4}-)?[0-9]{7,8}";

        //执行更新操作
        self.update = function () {
            departmentService.update($scope.data, function () {
                CommonService.success();
                $state.go($state.current, '', {reload: true});
            });
        };

        //统一暴露方法
        $scope.update = self.update;
    }]);
