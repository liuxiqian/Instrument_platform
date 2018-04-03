'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFiledeviceinstrumentIndexCtrl
 * @description
 * # StandardFiledeviceinstrumentIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('StandardFiledeviceinstrumentIndexCtrl',
        function ($scope, DeviceInstrumentService, $stateParams, config, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            // 初始化数据
            self.init = function () {
                $scope.params = self.scopeParamsInit();
                $scope.$watch('params.deviceSet', self.watchDeviceSet);
                self.load();
            };

            self.scopeParamsInit = function () {
                return {
                    page: 0,
                    size: config.size,
                    deviceSet: {id: $stateParams.deviceSetId}
                };
            };

            self.generateQueryParams = function() {
                return {
                    page: $scope.params.page,
                    size: $scope.params.size
                };
            };

            self.load = self.reload = function () {
                DeviceInstrumentService.pageByDeviceSetIdOfCurrentUser($scope.params.deviceSet.id, self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            // 当标准装置变化时，进行数据重新请求
            self.watchDeviceSet = function () {
                $scope.params.page = 0;
                self.load();
            };

            // 是否显示多个 标准装置
            self.showManyDeviceSet = function () {
                if ($scope.params.deviceSet.id) {
                    return false;
                } else {
                    return true;
                }
            };

            // 是否显示添加按钮
            self.showAdd = function () {
                return !self.showManyDeviceSet();
            };

            // 删除功能实现
            self.delete = function (index, deviceInstrument) {
                // 提示用户是否确定删除
                CommonService.warning(function (success, error) {
                    //请求后台，删除数据
                    DeviceInstrumentService.delete(deviceInstrument.id, function (status) {
                        if (204 === status) {
                            // 把该记录从视图的数组中移除
                            $scope.data.content.splice(index, 1);
                            // 提示用户删除成功
                            success();
                        } else {
                            // 中间表无关联删除的异常，如删除失败，应是网络问题
                            error('error', '系统或网络异常', '');
                        }
                    });
                });
            };

            self.init();
            // 是否显示删除按钮
            self.showDelete = self.showAdd;
            $scope.showManyDeviceSet = self.showManyDeviceSet;
            $scope.showAdd = self.showAdd;
            $scope.showDelete = self.showDelete;
            $scope.delete = self.delete;
        });
