'use strict';

/**
 * @ngdoc function
 * @名称 webappApp.controller:StandardFileIndexCtrl
 * @描述：控制V层数据的显示样式，并且调用M层的方法获取后台数据。
 * # StandardFileIndexCtrl——“标准装置-档案查询”的C层
 * Controller of the webappApp（控制器)
 */
angular.module('webappApp')
    .controller('StandardFileIndexCtrl',
        ['$scope', 'standardFileService', 'config', 'UserServer', 'CommonService',
            function ($scope, standardFileService, config, UserServer, CommonService) {
                var self = this;
                CommonService.initControllerPage(self, $scope);

                self.init = function () {
                    $scope.params = self.initScopeParams();
                    self.load();
                };

                // 生成查询参数
                self.generateQueryParams = function () {
                    return {
                        page: $scope.params.page,
                        size: $scope.params.size,
                        districtId: $scope.params.district.id,
                        departmentId: $scope.params.department.id,
                        name: $scope.params.name,
                        code: $scope.params.code
                    };
                };

                // 获取所有数据
                self.load = function () {
                    standardFileService.pageAllOfCurrentUserBySpecification(self.generateQueryParams(), function (data) {
                        $scope.data = data;
                    });
                };

                // 删除数据
                self.delete = function (index, object) {
                    // 提示用户是否确认删除
                    CommonService.warning(function (success, error) {
                        //向后台发出删除数据的请求
                        standardFileService.delete(object, function (status) {
                            if (204 === status) {
                                //从数组中删除数据
                                $scope.data.content.splice(index, 1);
                                // 提示用户删除成功
                                success();
                            } else {
                                error('error', '系统或网络异常', '');
                            }
                        });

                    });
                };

                self.init();
                //根据当前页面是否显示用户判断权限
                $scope.isShow = {};
                $scope.isShow.Query = true;
                $scope.isShow.Add = false;
                $scope.isShow.Operation = false;
                self.showQuxian = UserServer.showQuxian;
                self.showShi = UserServer.showShi;

                //统一暴露方法
                $scope.delete = self.delete;
                $scope.showQuxian = self.showQuxian;
                $scope.showShi = self.showShi;
                $scope.submit = self.submit;
                $scope.console = console;
            }]);
