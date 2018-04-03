'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiInstrumentName
 * @description
 * # yunzhiInstrumentName
 * 器具名称指令
 * zhangxishuo
 * 使用可以参考文件views/forceInstrumentStatistics/checkRate/index.html
 */
angular.module('webappApp')
    .directive('yunzhiInstrumentName', function(MandatoryInstrumentTypeService) {
        return {
            templateUrl: 'views/directive/yunzhiInstrumentName.html',
            restrict: 'E',
            scope: {
                ngModel: '='                      // 为该指令声明独立的作用域，同时进行双向绑定
            },
            link: function postLink(scope) {
                var self = this;

                self.init = function() {          // 初始化
                    self.getData();               // 请求数据
                    self.watch();                 // 监听名称选择
                };

                self.getData = function() {
                    scope.instrumentType = {};    // 初始化待选中器具名称空对象
                    scope.instrumentTypes = [];   // 初始化所有器具二级类别
                    MandatoryInstrumentTypeService.getAll(function(data) {
                        scope.instrumentTypes = data;   // 获取器具二级类别列表
                    });
                };

                self.watch = function() {         // 监听选择
                    scope.$watch('instrumentType', function(newValue) {
                        if (newValue.selected && newValue.selected.id) {
                            scope.ngModel = newValue.selected;    // 如果选中，将该值赋给ngModel
                        } else {
                            scope.ngModel = { name: '请选择' };    // 否则，返回一个初始化的名称对象
                        }
                    }, true);
                };

                self.init();
            }
        };
    });
