'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ForceinstrumentstatisticsCheckabilityIndexCtrl
 * @description
 * @Author poshichao
 * 检定能力统计 首页
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('ForceInstrumentStatisticsCheckAbilityIndexCtrl', function($scope, $http, CheckAbilityStatisticsService, $timeout, cfpLoadingBar, config) {
        var self = this;

        self.init = function() {
            $scope.params = {
                district: { id: undefined }, // 区域
                discipline: { id: undefined }, // 学科类别
                minMeasuringScale: { weight: undefined }, // 最小测量范围
                maxMeasuringScale: { weight: undefined }, // 最大测量范围
                accuracy: { id: undefined }, // 精确度
                instrumentType: { id: undefined } // 器具名称
            };
        };

        self.getQueryParam = function() {
            return {
                districtId: $scope.params.district.id, // 区域
                departmentId: $scope.params.department.id, // 器具用户(部门)
                disciplineId: $scope.params.discipline.id, // 学科
                instrumentTypeId: $scope.params.instrumentType.id, // 器具类别
                minMeasuringScaleWeight: $scope.params.minMeasuringScale.weight, // 最小测量范围权重
                maxMeasuringScaleWeight: $scope.params.maxMeasuringScale.weight, // 最大测量范围权重
                accuracyId: $scope.params.accuracy.id // 精确度等级
            };
        };

        self.load = self.reload = function() {
            // 根据传入的查询参数不同,使用不同的查询方法
            if ($scope.params.district.name !== "请选择" && $scope.params.discipline.name !== "请选择") {
                    if ($scope.params.instrumentType.name !== "请选择") {
                        if ($scope.params.minMeasuringScale.value !== "请选择" && $scope.params.maxMeasuringScale.value !== "请选择") {
                            console.log("按所有条件查找");
                        } else {
                            self.getAllByDistrictAndInstrumentType();
                        }
                    } else {
                        self.getAllByDistrictAndDiscipline();
                    }
            }
            // CheckAbilityStatisticsService.getAllBySpecification(self.getQueryParam, function(data) {
            //     $scope.checkAbilityStatistics = data;
            // });
        };

        // 器具检定能力表 数据初始化(测试用)
        self.dataInit = function() {
            CheckAbilityStatisticsService.dataInit(function() {
                cfpLoadingBar.start();
                self.checkData();
            });
        };

        // 进行检查,检查函数是否执行完成(用于执行时间较长的函数)
        // 函数未执行完成:再次调用这个函数,继续请求检查
        // 函数执行完成:加载界面结束
        self.checkData = function() {
            $timeout(function() {
                // 调用请求,判断函数是否执行完
                CheckAbilityStatisticsService.hasFinished(function(result) {
                    // 如果结果为未执行完,再次调用自己
                    if (!result.data) {
                        self.checkData();
                    } else {
                    // 如果结果为完成时,更新状态
                        cfpLoadingBar.complete();
                    }
                });    
            }, 1000);
        };

        // 通过区域和学科类别进行查询
        self.getAllByDistrictAndDiscipline = function() {
            CheckAbilityStatisticsService.getAllByDistrictAndDiscipline(self.getQueryParam, function(data) {
                $scope.checkAbilityStatistics = data.data;
            })
        };

        // 通过区域和器具类别进行查询
        self.getAllByDistrictAndInstrumentType = function() {
            CheckAbilityStatisticsService.getAllByDistrictAndInstrumentType(self.getQueryParam, function(data) {
                $scope.checkAbilityStatistics = data.data;
            })
        }

        self.init();
        
        $scope.debug = config.debug;
        $scope.reload = self.reload;
        $scope.dataInit = self.dataInit;
    });
