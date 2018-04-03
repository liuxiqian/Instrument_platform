'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiMeasuringRange
 * @description 测量范围 指令
 * # yunzhiMeasuringScale
 */
angular.module('webappApp')
    .directive('yunzhiMeasuringScale', ['MeasuringScaleService', function (MeasuringScaleService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                ngModel: '=',           //将使用此指令中的measuringRange属性，双向绑定到$scope.measuringRange
                instrumentType: '='         // 器具类别
            },
            templateUrl: 'views/directive/yunzhiMeasuringRange.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function ($scope) {
                $scope.measuringRanges = [];                        //初始化所有测量范围
                $scope.measuringRange = {};                         //初始化测量范围
                $scope.measuringRange.selected = $scope.ngModel;    //传值

                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function (newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {

                scope.$watch('instrumentType', function (newValue) {
                    var data = {value: '请选择'};
                    if (newValue && newValue.id) {
                        //获取用户可见的测量范围列表
                        MeasuringScaleService.getAllByInstrumentTypeId(newValue.id, function (datas) {
                            scope.measuringRanges = datas;
                            // 如果大小不为0，而且用户没有传入ngModel实体，则将第一个测量范围给当前测量范围（或当传入器具id，则初始化测量范围）
                            scope.ngModel = scope.measuringRange.selected = data;
                        });
                    } else {
                        //隐藏 测量范围，则将 选中的 测量范围 初始化，并传给V层，供搜索使用。
                        scope.measuringRanges = [data];
                        scope.measuringRange.selected = data;
                        scope.ngModel = scope.measuringRange.selected;
                    }
                }, true);
            }
        };
    }]);
