'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryQualifiedIndexCtrl
 * @description
 * # MandatoryQualifiedIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryQualifiedIndexCtrl', ['$scope', '$stateParams', 'config', 'InstrumentCheckInfoService', 'UserServer', 'CommonService', function ($scope, $stateParams, config, InstrumentCheckInfoService, UserServer, CommonService) {
        var self = this;

        self.init = function () {
            self.scopeParamsInit();
            self.load();
        };

        /**
         * 初始化scope的查询参数
         * panjie
         */
        self.scopeParamsInit = function () {
            $scope.params = {
                page: 0,
                size: config.size,
                district: {id: undefined},                                  // 区域
                department: {id: undefined},                                // 器具用户
                checkDepartment: {id: undefined},                           // 技术机构
                checkResult: {id: undefined},                               // 检定结果
                discipline: {id: undefined},                                // 学科类别
                instrumentTypeFirstLevel: {id: undefined},                  // 一级类别
                instrumentType: {id: undefined},                            // 二级类别
                name: '',                                                   // 器具名称
                certificateNum: '',                                         // 证书编号
                year: undefined,                                            // 年度
                mandatoryInstrumentId: $stateParams.mandatoryInstrumentId,  // 器具系统编号
                minMeasuringScale: {weight: undefined},                     // 最小测量范围
                maxMeasuringScale: {weight: undefined},                     // 最大测量范围
                accuracy:{id: undefined}                                    // 精确度
            };
        };

        // 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
        self.getQueryParam = function () {
            return {
                page: $scope.params.page,
                size: $scope.params.size,
                districtId: $scope.params.district.id,                                  // 区域
                departmentId: $scope.params.department.id,                              // 器具用户(部门)
                checkDepartmentId: $scope.params.checkDepartment.id,                    // 技术机构
                checkResultId: $scope.params.checkResult.id,                            // 检定结果
                disciplineId: $scope.params.discipline.id,                              // 学科
                instrumentTypeFirstLevelId: $scope.params.instrumentTypeFirstLevel.id,  // 一级类别
                instrumentTypeId: $scope.params.instrumentType.id,                      // 器具类别
                name: $scope.params.name,                                               // 器具名称
                certificateNum: $scope.params.certificateNum,                           // 证书编号
                year: $scope.params.year,                                               // 年度
                mandatoryInstrumentId: $scope.params.mandatoryInstrumentId,             // 强检器具id /系统编号
                minMeasuringScaleWeight: $scope.params.minMeasuringScale.weight,        // 最小测量范围权重
                maxMeasuringScaleWeight: $scope.params.maxMeasuringScale.weight,        // 最大测量范围权重
                accuracyId: $scope.params.accuracy.id
            };
        };

        //获取所有数据
        self.load = self.reload = function () {
            // 按照查询条件进行查找
            InstrumentCheckInfoService.pageAllOfManagementDepartmentBySpecification(self.getQueryParam(), function (data) {
                $scope.data = data;
            });
        };

        /**
         * 重新加载数据
         * @param page 当前页
         */
        self.reloadByPage = function (page) {
            $scope.params.page = page;
            self.reload();
        };

        /**
         * 重新加载数据
         * @param size
         */
        self.reloadBySize = function (size) {
            $scope.params.size = size;
            self.reload();
        };

        // 是否显示区县
        self.showQuxian = function () {
            return UserServer.showQuxian();
        };

        // 是否显示器具用户
        self.showQijuyonghu = function () {
            return UserServer.showQijuyonghu();
        };

        // 检定信息的删除功能
        self.remove = function (index) {
            $scope.data.content.splice(index, 1);
        };

        // 删除功能
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

        //显示信息
        $scope.sendDepartment = true;
        $scope.checkDepartment = true;

        //统一暴露方法
        $scope.delete = self.delete;
        $scope.reload = self.reload;
        $scope.reloadByPage = self.reloadByPage;
        $scope.reloadBySize = self.reloadBySize;
        $scope.console = console;
        $scope.showQuxian = self.showQuxian;
        $scope.showQijuyonghu = self.showQijuyonghu;
    }]);
