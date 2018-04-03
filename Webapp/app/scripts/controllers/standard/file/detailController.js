'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFileDetailCtrl
 * @description
 * # StandardFileDetailCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFileDetailCtrl', ['$scope', 'standardFileService', '$stateParams', 'StandardDeviceService', 'UserServer', 'CommonService', function ($scope, standardFileService, $stateParams, StandardDeviceService, UserServer, CommonService) {
        var self = this;

        self.init = function() {
            // 路由继承，直接刷新页面时，先初始化父路由，再初始化子路由。
            // 共享$scope数据
            // 这导致了如下执行顺序：
            // 1. 父路由初始化工作完成（进行了10次渲染)
            // 2. $scope.data = {} 该工作父路由完成了
            // 3. 子路由继承了父路由的$scope
            // 4. 子路由渲染开始
            // 5. 由于子路由的data格式要求是数组，而当前数据是对象。导致控制台报错
            // 6. 子路由对DATA重新赋值后，$scope.data = []; 所以我们使用控制台找不到任何的出错信息
            // 这是UI-ROUTE继续时遇到的最大的坑.
            $scope.data = [];
            self.checkAccess();
            self.getAllStandardDevice();
        };

        //获取所有的数据
        self.getAllStandardDevice = function () {
            $scope.deviceSet = {id: $stateParams.deviceSetId};
            $scope.params.hideAdd = $stateParams.hideAdd;

            standardFileService.getAllByDeviceSetId($scope.deviceSet.id, function (data) {
                $scope.data = data;
            });
        };


        //删除标准器
        self.delete = function (standardDeviceId) {
            // 提示用户是否确认删除
            CommonService.warning(function (success, error) {
                // 删除数据
                StandardDeviceService.delete(standardDeviceId, function (status) {
                    if (204 === status) {
                        //需要对数组进行遍历，因为用到了过滤器,所以index将不准确
                        angular.forEach($scope.data, function (data, index) {
                            if (data.id === standardDeviceId) {
                                $scope.data.splice(index, 1);
                            }
                        });

                        // 提示用户删除成功
                        success();
                    } else {
                        error('error', '清先删除关联的记录', '');
                    }
                });
            });

        };

        //判断权限,是技术机构还是管理部门
        self.checkAccess = function() {
            UserServer.getCurrentLoginUser(function (user) {
                if (user.department.departmentType.name === "管理部门") {
                    //管理部门只有查看权限
                    $scope.isShowAddOrOperation = false;
                } else {
                    //技术机构有增删改查权限
                    $scope.isShowAddOrOperation = true;
                }
            });
        };

        self.init();
        //统一暴露方法
        $scope.delete = self.delete;
    }]);
