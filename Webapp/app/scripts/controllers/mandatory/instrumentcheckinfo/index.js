'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryInstrumentcheckinfoIndexCtrl
 * @description
 * # MandatoryInstrumentcheckinfoIndexCtrl
 * 强检器具检定管理
 */
angular.module('webappApp')
    .controller('MandatoryInstrumentcheckinfoIndexCtrl',
        ['$scope', '$stateParams', 'config', 'InstrumentCheckInfoService', 'UserServer', 'CommonService', function ($scope, $stateParams, config, InstrumentCheckInfoService, UserServer, CommonService) {
            var self = this;
            CommonService.initControllerPage(self, $scope);

            self.init = function () {
                $scope.params = self.initScopeParams();
                self.load();
            };

            self.initScopeParams = function () {
                return {
                    page: 0,
                    size: config.size,
                    district: {},          // 区域
                    department: {},      // 器具用户
                    discipline: {},  // 学科类别
                    instrumentTypeFirstLevel: {},    // 一级类别
                    instrumentType: {},  // 二级类别
                    checkResult: {},           // 检定结果
                    accuracyDisplayName: {},           // 精确度显示名称
                    name: '',           // 强检器具名称
                    certificateNum: '',           // 强检器具名称
                    year: undefined,            // 年度
                    mandatoryInstrumentId: undefined
                };
            };

            self.generateQueryParams = function () {
                return {
                    page: $scope.params.page,
                    size: $scope.params.size,
                    districtId: $scope.params.district.id,          // 区域
                    departmentId: $scope.params.department.id,    // 器具用户(部门)
                    disciplineId: $scope.params.discipline.id,            //学科
                    instrumentTypeFirstLevelId: $scope.params.instrumentTypeFirstLevel.id,      //一级类别
                    instrumentTypeId: $scope.params.instrumentType.id,        //器具类别
                    checkResultId: $scope.params.checkResult.id,        //检定结果
                    accuracyDisplayNameId: $scope.params.accuracyDisplayName.id,        //检定结果
                    name: $scope.params.name,      // 器具名称
                    year: $scope.params.year,        // 年度
                    mandatoryInstrumentId: $scope.params.mandatoryInstrumentId,      // 强检器具id /系统编号
                    certificateNum: $scope.params.certificateNum      // 强检器具id /系统编号
                };
            };

            //获取所有数据
            self.load = self.reload = function () {
                // 按照查询条件进行查找
                InstrumentCheckInfoService.pageAllOfTechnicalInstitutionDepartmentBySpecification(self.generateQueryParams(), function (data) {
                    $scope.data = data;
                });
            };

            // 提交数据
            self.submit = function () {
                self.reload();
            };

            $scope.isShow = {};
            $scope.isShow.operation = true;
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

            // 是否显示区县
            self.showQuxian = function () {
                return UserServer.showQuxian();
            };

            // 是否显示器具用户
            self.showQijuyonghu = function () {
                return UserServer.showQijuyonghu();
            };


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

            // 隐藏检定机构字段
            self.hideCheckDepartment = function () {
                return true;
            };

            // 执行获取数据
            self.init();
            //统一暴露方法
            $scope.delete = self.delete;
            $scope.submit = self.submit;
            $scope.console = console;
            $scope.hideCheckDepartment = self.hideCheckDepartment;
            $scope.sendDepartment = true;
            $scope.checkDepartment = false;
            $scope.showQuxian = self.showQuxian;
            $scope.showQijuyonghu = self.showQijuyonghu;
        }]);
