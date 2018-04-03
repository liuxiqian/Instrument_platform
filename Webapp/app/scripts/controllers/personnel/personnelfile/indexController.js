'use strict';
/**
 * @ngdoc function
 * @name webappApp.controller:PersonnelPersonnelfileIndexCtrl
 * @人员资质管理综合查询
 * # PersonnelPersonnelfileIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('PersonnelPersonnelfileIndexCtrl',
        ['$scope', 'CommonService', 'UserServer', 'StandardPersonnelFileService',
            function ($scope, CommonService, UserServer, StandardPersonnelFileService) {
                var self = this;
                CommonService.initControllerPage(self, $scope);

                //设置分页信息
                self.init = function () {

                    self.load();

                    self.showQuxian = UserServer.showQuxian;
                    self.showShi = UserServer.showShi;

                    //判断当前页面是否显示技术机构字段
                    $scope.isShow = {};
                    $scope.isShow.Department = false;

                    $scope.showQuxian = self.showQuxian;
                    $scope.showShi = self.showShi;
                    $scope.delete = self.delete;
                };


                //获取所有数据
                self.load = function () {
                    StandardPersonnelFileService.getAllByCurrentLoginUser($scope.params, function (data) {
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
