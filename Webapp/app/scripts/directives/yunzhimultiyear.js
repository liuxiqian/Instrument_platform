'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiMultiYear
 * @description
 * # yunzhiMultiYear
 * zhangxishuo
 * 年度多选框指令
 * 详细使用可以参考 views/forceInstrumentStatistics/checkRate/index.html
 */
angular.module('webappApp')
    .directive('yunzhiMultiYear', function() {
        return {
            templateUrl: 'views/directive/yunzhiMultiYear.html',
            restrict: 'E',
            scope: {              // 设置独立scope
                ngModel: '='      // 双向绑定,该指令需绑定一个数组
            },
            link: function postLink(scope) {
                var self = this;

                self.init = function() {
                    scope.years = self.initYear();         // 初始化年份
                };

                /**
                 * 初始化自2017年至今的所有年份数组
                 * zhangxishuo
                 */
                self.initYear = function() {
                    var years       = [];                  // 初始化空数组
                    var beginYear   = 2017;                // 起始年份
                    var today       = new Date();          // 获取当前日期
                    var currentYear = today.getFullYear(); // 获取当前年份

                    for (var i = beginYear; i <= currentYear; i++) {
                        var year = i;                      // 初始化某年
                        years.push(year);                  // 存入数组
                    }
                    return years;
                };

                /**
                 * 切换年度的选中状态
                 * zhangxishuo
                 */
                self.toggle = function(year) {
                    var index = scope.ngModel.indexOf(year);  // 获取该年度在数组中的索引
                    if (index > -1) {
                        scope.ngModel.splice(index, 1);       // 数组中存在，删除该年
                    } else {
                        scope.ngModel.push(year);             // 数值中不存在
                    }
                };

                scope.toggle = self.toggle;

                self.init();
            }
        };
    });
