'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ForceinstrumentstatisticsCheckrateIndexCtrl
 * @description
 * # ForceinstrumentstatisticsCheckrateIndexCtrl
 * Controller of the webappApp
 * 强检器具受检率统计 数据查询
 * zhangxishuo
 */
angular.module('webappApp')
    .controller('ForceinstrumentstatisticsCheckrateIndexCtrl', function($scope, $timeout, CommonService, forceInstrumentCheckRateService) {
        var self = this;

        self.init = function() {
            $scope.params = {
                years: [],                                     // 年份
                district: {id: undefined},                     // 区域
                instrumentUserDepartment: {id: undefined},     // 器具用户
                purpose: {id: undefined},                      // 用途
                instrumentType: {id: undefined}                // 器具名称
            };
            self.initChart();                                  // 初始化图表
        };

        self.initChart = function() {
            // 为图表区域设置一个不同的id，用于获取节点
            $scope.checkedRateByYearsChartId = CommonService.getUniqueId();
            $scope.checkedRateByYearChartId = CommonService.getUniqueId();
        };

        // 查询多年数据
        self.queryAllCheckedRateInfoByYears = function() {
            // 调用Service中的查询方法
            forceInstrumentCheckRateService.queryByYears($scope.params.years, $scope.params.district, $scope.params.instrumentUserDepartment, $scope.params.purpose, $scope.params.instrumentType, function(data) {
                // 将结果传给V层
                $scope.checkedRateInfos = data;

                // 比较，看是否选中了省或市
                var selectProvince = $scope.params.district.districtType.name === '省';
                var selectCity = $scope.params.district.districtType.name === '市';
                // 如果选中了省或市
                if (selectProvince || selectCity) {
                    // 显示并渲染多年图表
                    $scope.showYearsChart = true;
                    $timeout(self.generateChartByYears, 500);
                } else {
                    // 隐藏图表
                    $scope.showYearsChart = false;
                }
            });
        };

        // 查询单年数据
        self.queryAllCheckedRateInfoByYear = function() {
            // 调用Service中的查询方法
            forceInstrumentCheckRateService.queryByYear($scope.params.years, $scope.params.district, $scope.params.purpose, $scope.params.instrumentType, function(data) {
                // 将结果传给V层
                $scope.checkedRateInfos = data;

                // 判断是否选中了省
                var selectProvince = $scope.params.district.districtType.name === '省';
                // 如果选中了省
                if (selectProvince) {
                    // 显示并渲染图表
                    $scope.showYearChart = true;
                    $timeout(self.generateChartByDistricts, 500);
                } else {
                    // 隐藏图表
                    $scope.showYearChart = false;
                }
            });
        };

        // 生成多年度的图表
        self.generateChartByYears = function() {
            // 初始化数组
            var years = [];
            var rates = [];
            // 循环数据，设置数组
            angular.forEach($scope.checkedRateInfos, function(element) {
                years.push(element.year);
                rates.push(self.calculateRate(element.checkedTotal, element.instrumentTotal));
            });
            // 渲染图表
            //self.renderChart($scope.checkedRateByYearsChartId, years, rates);
            // 返回图表指令需要得数据
            $scope.chartXData = years;
            $scope.chartDarData = rates;
            $scope.chartName = $scope.params.district.name + '强检器具受检率统计';
            $scope.chartYMax = 100;
            $scope.chartYMin = 0;
        };

        // 生成多区域的图表
        self.generateChartByDistricts = function() {
            // 初始化数组
            var cities = [];
            var rates = [];
            // 循环数据，设置数组
            angular.forEach($scope.checkedRateInfos, function(element) {
                cities.push(element.cityDistrict.name);
                rates.push(self.calculateRate(element.checkedTotal, element.instrumentTotal));
            });
            // 渲染图表
            //self.renderChart($scope.checkedRateByYearChartId, cities, rates);
            $scope.chartXData = cities;
            $scope.chartDarData = rates;
            $scope.chartName = $scope.params.district.name + '强检器具受检率统计';
            $scope.chartYMax = 100;
            $scope.chartYMin = 0;
        };

        // 计算受检率
        self.calculateRate = function(checkedTotal, instrumentTotal) {
            // 四舍五入保留两位小数，与percent指令原理相同
            return Math.round(checkedTotal / instrumentTotal * 10000) / 100;
        };

        /**
         * 渲染图表
         * @param  {[type]} chartId 待渲染图表的DOM id
         * @param  {[type]} xAxis   x轴要显示的年度/城市名称
         * @param  {[type]} data    与x轴对应的数据
         * zhangxishuo
         */
        // self.renderChart = function(chartId, xAxis, data) {
        //     // 初始化图表并设置高度与宽度
        //     var chart = echarts.init(document.getElementById(chartId));
        //     CommonService.setEChartsHeightAndWidth(chart, chartId);

        //     // 设置图表的各项参数
        //     var option = {
        //         title: {
        //             text: $scope.params.district.name + '强检器具受检率统计'
        //         },
        //         tooltip: {},
        //         legend: {
        //             data: ['强检器具受检率']
        //         },
        //         xAxis: {
        //             data: xAxis
        //         },
        //         yAxis: {
        //             type: 'value',
        //             min: 0,
        //             max: 100
        //         },
        //         series: {
        //             name: '强检器具受检率',
        //             type: 'bar',
        //             itemStyle: {
        //                 normal: {
        //                     color: function(params) {
        //                         var colorList = [
        //                             '#E87E0C', '#FF0000', '#8B0CE8', '#0D68FF', '#31353D'
        //                         ];
        //                         return colorList[params.dataIndex % colorList.length];
        //                     }
        //                 }
        //             },
        //             barWidth: 70,
        //             data: data
        //         }
        //     };
        //     // 渲染图表
        //     chart.setOption(option);
        // };

        // 重新计算器具用户是否显示
        self.reJudgeInstrumentUser = function() {
            // 判断是否是多年度和是否是区县
            var isMultiYears = $scope.params.years.length > 1;
            var isCounty = false;
            // 如果选中了区域，才进行判断，防止渲染时报错
            if (typeof($scope.params.district.id) !== 'undefined') {
                isCounty = $scope.params.district.districtType.name === '区\\县';
            }
            // 既是多年度又是区县
            if (isMultiYears && isCounty) {
                // 显示器具用户指令
                $scope.showInstrumentUser = true;
            } else {
                // 隐藏器具用户指令
                $scope.showInstrumentUser = false;
            }
        };

        // 监听选中年度，根据不同的选择显示不同查询按钮
        $scope.$watch('params.years.length', function(newValue) {
            if (newValue > 1) {
                // 选中多年，显示多年查询按钮
                $scope.showByYears = true;
                $scope.showByYear = false;
            } else {
                // 选中一年，显示单年查询按钮
                $scope.showByYears = false;
                $scope.showByYear = true;
            }
            // 重新计算是否显示器具用户
            self.reJudgeInstrumentUser();
        });

        // 监听区域，判断是否显示器具用户
        $scope.$watch('params.district', function() {
            self.reJudgeInstrumentUser();
        });

        $scope.queryAllCheckedRateInfoByYears = self.queryAllCheckedRateInfoByYears;
        $scope.queryAllCheckedRateInfoByYear = self.queryAllCheckedRateInfoByYear;

        self.init();
    });
