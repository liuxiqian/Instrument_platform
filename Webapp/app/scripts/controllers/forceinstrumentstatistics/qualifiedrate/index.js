'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ForceinstrumentstatisticsQualifiedrateIndexCtrl
 * @description
 * # ForceinstrumentstatisticsQualifiedrateIndexCtrl
 * Controller of the webappApp
 * 强检器具鉴定合格率统计
 * zhuchenshu
 */
angular.module('webappApp')
    .controller('ForceinstrumentstatisticsQualifiedrateIndexCtrl', function ($scope, CommonService, forceInstrumentStatisticsQualifiedrateService, $timeout) {
        var self = this;

        self.init = function() {
            // 省市的初始化
            $scope.hideDepartment = true;
            $scope.dataBack = true; // 初始化在后台数据未返回的时候不显示
            $scope.oneYearChart = true;
            $scope.manyYearChart = true;
            self.provice = '省';
            self.city = '市';
            self.country = '区\\县';

            $scope.params = {                       // 初始化数据
                years: [],                          // 年份
                district: {},                       // 区域
                department: {},                     // 器具用户
                purpose: {},                        // 用途
                intrumentType: {},                 // 器具名称（类别）
                generativeDepartment:{}             // 生产企业
            };
        };

        // 生成查询参数
        self.generateQueryParams = function () {
            return {
                purpose: $scope.params.purpose.id,             // 用途
                intrumentType: $scope.params.intrumentType.id,       // 器具名称
                generativeDepartment: $scope.params.generativeDepartment.id,   // 生产企业
            };
        };

        //获取所有数据
        self.reload = function () {
            $scope.hideChart = false; // 默认显示图表
            // 判断只有在选择了年份和区域后再请求数据
            if ($scope.params.district.name !== '请选择') {
                if ($scope.params.years.length > 1) {   // 如果选中了多年
                    // 如果选中了区县级区域
                    if ($scope.params.district.districtType.name === self.country) {
                        // 判断是否选中器具用户
                        if ($scope.params.department.name !== '请选择') {
                            self.quaryByYearsAndDistrictAndDepartment();
                        } else {
                            self.quaryByYearsAndDistrict();
                        }
                    } else {
                        // 如果选中了省市级区域
                        self.quaryByYearsAndDistrict();
                    }
                } else if($scope.params.years.length === 1) {                                // 如果选中了某年
                    // 选取一个年份一个区域进行查询
                    self.quaryByYearsAndDistrict();
                } 
            }
        };
        // 检测年份
        $scope.$watch('params.district',function() {
            self.judgeDistrictAndYear();
        });
        // 检测区域类型
        $scope.$watch('params.years.length',function() {
            self.judgeDistrictAndYear();
        });
        // 判断只有当选中多年及区域主体类型为区县时，才将器具用户显示出来
        self.judgeDistrictAndYear = function () {
            // 判断数据是否已经加载，在初始加载数据是$scope.params.district的值为{}
            if (!(Object.prototype.isPrototypeOf($scope.params.district) && Object.keys($scope.params.district).length === 0)) {
                 if ($scope.params.district.name === '请选择') {
                    $scope.hideDepartment = true;
                } else {
                    if ($scope.params.district.districtType.name === self.country && $scope.params.years.length > 1) {
                        $scope.hideDepartment = false;
                    } else {
                        $scope.hideDepartment = true;
                    }
                }
            }
        };

        // 选择多个年度及1个区域主体，进行查询
        self.quaryByYearsAndDistrict = function () {
            forceInstrumentStatisticsQualifiedrateService.quaryByYearsAndDistrictAndParams($scope.params.years, $scope.params.district,  self.generateQueryParams(), function (data) {
                $scope.datas = data;
                $scope.dataBack = false;
                self.backParams(); // 取消显示数据的双向绑定
                self.getChartData();// 获取传入图标指令的数据
            });
        };

        // 选择多个年度及1个区域主体及器具用户，进行查询
        self.quaryByYearsAndDistrictAndDepartment = function () {
            forceInstrumentStatisticsQualifiedrateService.quaryByYearsAndDistrictAndDepartmentAndParams($scope.params.years, $scope.params.district, $scope.params.department, self.generateQueryParams(), function (data) {
                $scope.datas = data;
                $scope.dataBack = false;
                self.backParams(); // 取消显示数据的双向绑定
                self.getChartData();// 获取传入图标指令的数据
            });
        };
        // 取消非后台返回数据的双向数据绑定
        self.backParams = function() {
            var purpose = $scope.params.purpose;
            var intrumentType = $scope.params.intrumentType;
            var generativeDepartment = $scope.params.generativeDepartment;
            $scope.backGenerativeDepartment = generativeDepartment;
            $scope.backIntrumentType = intrumentType;
            $scope.backPurse = purpose;
        };

        self.getChartData = function() {
            $scope.chartYMax = 100;
            $scope.chartYMin = 0;

            var xAxis = []; // x轴内容
            var qualifiedRates = []; // 柱状图内容

            var title = '检定合格率';
            var titleText = $scope.params.district.name + title;

            if ($scope.params.district.districtType.pinyin === 'quxian' && $scope.params.years.length === 1) {
                $scope.hideChart = true;// 当显示数据为单年的区县时隐藏柱状图
            }

            for(var i = 0;i<$scope.datas.length;i++) {
                var data = $scope.datas[i];
                if ($scope.params.years.length > 1) {
                    xAxis.push(data.year);
                    // 如果查询器具用户时将表名替换为器具用户
                    if ($scope.params.district.districtType.pinyin === 'quxian' && $scope.params.department.name !== '请选择') {
                        var titleText = $scope.params.department.name + title;
                    }
                } else {
                    if ($scope.params.district.districtType.pinyin === 'quxian') {
                        xAxis.push(data.department.name);
                    } else {
                        xAxis.push(data.district.name);
                    }
                }
                
                qualifiedRates.push(self.generateRate(data.sum,data.qualifiedNumber));
            }

            $scope.chartXData = xAxis;
            $scope.chartDarData = qualifiedRates;
            $scope.chartName = titleText;
        };

        // 获取合格率
        self.generateRate = function(sum, qualifiedNumber) {
            if (sum === 0) {
                return 0;
            } else {
                return qualifiedNumber/sum*100;
            }
        };

        self.init();
        $scope.reload = self.reload;
    });
