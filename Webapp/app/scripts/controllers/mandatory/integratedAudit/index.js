'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryIntergratedauditIndexCtrl
 * @description
 * 强制检定器具 -- 器具检定管理
 * # MandatoryIntergratedauditIndexCtrl
 * Controller of the webappApp
 * @author panjie@yunzhiclub.com
 */
angular.module('webappApp')
    .controller('MandatoryIntegratedAuditIndexCtrl', ['$scope', '$stateParams', 'config', 'InstrumentCheckInfoService', 'UserServer', 'CommonService',
        function ($scope, $stateParams, config, InstrumentCheckInfoService, UserServer, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                $scope.params = self.initScopeParams();
                self.load();
            };

            self.generateQueryParams = function () {
                return {
                    page: $scope.params.page,
                    size: $scope.params.size,
                    disciplineId: $scope.params.discipline.id,            //学科
                    instrumentFirstLevelTypeId: $scope.params.instrumentFirstLevelType.id,      //一级类别
                    instrumentTypeId: $scope.params.instrumentType.id,        //器具类别
                    checkResultId: $scope.params.checkResult.id,        //检定结果
                    name: $scope.params.name,      // 器具名称
                    year: $scope.params.year,        // 年度
                    mandatoryInstrumentId: $scope.params.mandatoryInstrumentId,      // 强检器具id /系统编号
                    sort: 'id,desc'
                };
            };

            //获取所有数据
            self.load = function () {
                // 按照查询条件进行查找
                InstrumentCheckInfoService.pageAllOfMeasureUserBySpecification(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };


            //对权限进行区分
            // UserServer.getCurrentLoginUser(function (currentUser) {
            //     if (currentUser.department.departmentType.name === "技术机构") {
            //         //检定信息，技术机构有操作，增加的权限
            //         $scope.isShow.operation = true;
            //     } else {
            //         //管理部门用户和器具用户没有操作权限，只有增加权限
            //         $scope.isShow.operation = false;
            //     }
            // });

            //检定信息的删除功能
            self.remove = function (index) {
                $scope.data.content.splice(index, 1);
            };

            //删除功能
            self.delete = function (index, InstrumentCheckInfo) {
                //向后台发送请求，看是否有权限删除
                CommonService.warning(function (success, error) {
                    InstrumentCheckInfoService.delete(InstrumentCheckInfo.id, function () {
                        success();
                        //从数组中移除被删除的这一项
                        self.remove(index);
                    }, function () {
                        error('error', '您没有删除的权限', '');
                    });
                });
            };

            self.init();

            // 统一暴露方法
            $scope.isShow = {};
            $scope.isShow.operation = true;
            $scope.delete = self.delete;
            $scope.submit = self.submit;
            $scope.console = console;
            $scope.sendDepartment = false;
            $scope.checkDepartment = true;
            $scope.showQuxian = UserServer.showQuxian;
            $scope.showQijuyonghu = UserServer.showQijuyonghu;
        }]);
