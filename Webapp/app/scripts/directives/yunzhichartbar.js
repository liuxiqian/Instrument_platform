'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiChartBar
 * @description
 * # yunzhiChartBar
 * zhuchenshu
 * 柱状图指令
 * 外部数据：图表名称，图标x轴，图表y轴，图表bar内容（数据）
 * 内部数据： y轴，颜色...
 */
angular.module('webappApp')
    .directive('yunzhiChartBar', function($timeout, CommonService) {
        return {
            templateUrl: 'views/directive/yunzhichartbar.html',
            restrict: 'E',
            scope: {
                chartName: '=', // 柱状图的名称，如‘内蒙地区强检器具受检率统计’
                chartXdata: '=', // 柱状图的x轴的数据，如['赤峰市','测试市']
                chartYmax: '=', // 柱状图的y轴最大值，如100（显示100%）
                chartYmin: '=', // 柱状图的y轴的最小值，如0（显示0%）
                chartDar: '=' // 柱状图中的柱形的显示数据如[100,99,0],注：该处显示百分比若要显示100%，需传入100
            },
            link: function postLink(scope) {
                // 设置图表公共选项
                var option = {
                    title: {
                        text: {}
                    },
                    color: ['#62CB31'],
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b}:\n{c}%'
                    },
                    xAxis: [{
                        type: '',
                        data: [],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }],
                    yAxis: {
                        type: 'value',
                        min: '',
                        max: '',
                        axisLabel: {
                            show: true,
                            interval: 'auto',
                            formatter: '{value} %'
                        }
                    },
                    series: [{
                        name: '',
                        type: 'bar',
                        data: [],
                        itemStyle: {
                            normal: {
                                color: function(params) {
                                    var colorList = CommonService.getChartBarColor();
                                    return colorList[params.dataIndex % colorList.length];
                                },
                                label: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{b}\n{c}%'
                                }
                            }
                        },
                        axisLabel: {
                            show: true,
                            interval: 'auto',
                            formatter: '{value} %'
                        },
                        barWidth: 70
                    }]
                };
                // 获取随机的图表id
                scope.chartId = CommonService.getUniqueId();


                // 设置图标内容
                self.setChart = function() {
                    var chart = echarts.init(document.getElementById(scope.chartId));
                    CommonService.setEChartsHeightAndWidth(chart, scope.chartId);

                    var qualifiedRateOption = option; // 赋值标准模板
                    qualifiedRateOption.title.text = scope.chartName; // 默认表名为区域名
                    qualifiedRateOption.yAxis.max = scope.chartYmax; // 表格的y轴最大值
                    qualifiedRateOption.yAxis.min = scope.chartYmin; // 表格的y轴最小值
                    qualifiedRateOption.xAxis[0].data = scope.chartXdata; // x轴数据
                    qualifiedRateOption.series[0].data = scope.chartDar; // 柱形上的数据
                    chart.setOption(qualifiedRateOption);
                };
                // 监听传入的图表数据，当产生变化时，重新加载
                scope.$watch('[chartXdata, chartDar, chartName, chartYmin, chartYmax]', function(newValue) {
                    if (scope.chartDar) {  // 防止第一次数据为空时加载图表
                        $timeout(function() {
                            self.setChart();
                        }, 1000);
                    }
                }, true);
            }
        };
    });
