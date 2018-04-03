'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:UserUserfileIndexCtrl
 * @description 用户管理Indexcontroller
 * # UserUserfileIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('UserUserfileIndexCtrl', ['$scope', 'UserServer', '$location', 'CommonService', '$stateParams', 'config', '$state', function($scope, UserServer, $location, CommonService, $stateParams, config, $state) {
        var self = this;

        //隐藏查询条件
        self.showQuery = false;


        //获取所有数据
        self.init = function() {
            if (typeof($scope.init) !== 'undefined') {
                if (typeof($scope.init) === 'function') {
                    $scope.init();
                }
            } else {
                // 获取ui-route传入的参考
                $scope.params = self.params = {
                    page: $stateParams.page ? parseInt($stateParams.page) : 1,
                    size: $stateParams.size ? parseInt($stateParams.size) : config.size,
                    district: { id: parseInt($stateParams.districtId) }, // 区域
                    departmentType: { id: parseInt($stateParams.departmentTypeId) }, // 部门类型
                    status: { key: parseInt($stateParams.status) }, // 用户类型
                    departmentName: $stateParams.departmentName, // 部门名称
                    username: $stateParams.username, // 用户名
                    deparmentId: parseInt($stateParams.departmentId)
                };
                $scope.deparmentId = self.params.deparmentId;
                // 分页数据初始化
                CommonService.initPageData($scope);

                // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
                var params = {
                    page: self.params.page,
                    size: self.params.size,
                    districtId: self.params.district.id ? self.params.district.id : undefined,
                    departmentTypeId: self.params.departmentType.id ? self.params.departmentType.id : undefined,
                    status: (self.params.status.key !== '-2' && self.params.status.key !== -2) ? self.params.status.key : undefined,
                    departmentName: self.params.departmentName ? self.params.departmentName : undefined, //
                    username: self.params.username ? self.params.username : undefined, //
                    departmentId: self.params.deparmentId ? self.params.deparmentId : undefined
                };
                UserServer.pageAllBySpecification(params, function(data) {
                    $scope.data = data;
                });
            }
        };

        // 提交数据
        self.submit = function() {
            $stateParams.districtId = self.params.district.id;
            $stateParams.departmentTypeId = self.params.departmentType.id;
            $stateParams.status = self.params.status.key;
            $stateParams.departmentName = self.params.departmentName;
            $stateParams.username = self.params.username;
            $state.go($state.current, $stateParams, { reload: true });
        };

        // 重置密码
        self.resetPassword = function(user) {
            CommonService.warning(function(success, error) {
                UserServer.resetPassword(user.id, function(status) {
                    if (204 === status) {
                        success('success', '密码已重置为111111');
                    } else {
                        error('error', '系统或网络异常', '');
                    }
                });
            });
        };

        // 删除
        self.delete = function(index, user, data) {
            CommonService.warning(function(success, error) {
                UserServer.delete(user, function(status) {
                    if (204 === status) {
                        if (data) {
                            data.splice(index, 1);
                        } else {
                             // 从视图中删除该数据
                            $scope.data.content.splice(index, 1);
                        }

                        success();
                    } else {
                        error('error', '系统或网络异常', '');
                    }
                });
            });
        };

        // 执行获取数据
        self.init();

        // 方法统一暴露
        $scope.add = self.add;
        $scope.edit = self.edit;
        $scope.detail = self.detail;
        $scope.resetPassword = self.resetPassword;
        $scope.submit = self.submit;
        $scope.delete = self.delete;
        $scope.showQuery = self.showQuery;
    }]);
