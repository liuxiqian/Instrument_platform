'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardPersonnelfileIndexCtrl
 * @description   人员资质--综合查询
 * # StandardPersonnelfileIndexCtrl
 * @author chenyuanyuan
 */
angular.module('webappApp')
    .controller('StandardPersonnelfileIndexCtrl',
        ['$scope', 'CommonService', 'UserServer', 'StandardPersonnelFileService',
            function ($scope, CommonService, UserServer, StandardPersonnelFileService) {
                var self = this;
                CommonService.initControllerPage(self, $scope);

                self.init = function () {
                    $scope.params = self.initScopeParams();
                    self.load();

                    self.showQuxian = UserServer.showQuxian;
                    self.showShi = UserServer.showShi;

                    //判断当前页面是否显示操作字段
                    $scope.isShow = {};
                    $scope.isShow.Operation = false;

                    $scope.showQuxian = self.showQuxian;
                    $scope.showShi = self.showShi;
                    $scope.submit = self.submit;
                };


                self.generateQueryParams = function () {
                    // 整理请求参数
                    return {
                        page: $scope.params.page,
                        size: $scope.params.size,
                        districtId: $scope.params.district.id,
                        name: $scope.params.name,
                        departmentName: $scope.params.departmentName
                    };
                };

                // 获取所有数据
                self.load = function () {
                    StandardPersonnelFileService.pageAllOfCurrentUserBySpecification(self.generateQueryParams(), function (data) {
                        $scope.data = data;
                    });
                };

                // 删除功能
                self.delete = function (index, id) {
                    //提示用户
                    CommonService.warning(function (success, error) {
                        StandardPersonnelFileService.delete(id, function (response) {
                            if (204 === response.status) {
                                // 删除此条数据，更新视图
                                $scope.data.content.splice(index, 1);
                                success();
                            } else {
                                // 未删除关联实体
                                error('error', '请先删除与其相关联的其它记录', '');
                            }
                        });
                    });
                };

                self.init();
            }]);
