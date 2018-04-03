'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiAccuracy
 * @description 准确度等级 指令
 * # yunzhiAccuracy
 */
angular.module('webappApp')
    .directive('yunzhiAccuracy', ['$http', 'AccuracyService', function($http, AccuracyService) {
        return {
            //独立scope，使各个指令之间互不影响
            scope: {
                ngModel: '=', //将使用此指令中的accuracy属性，双向绑定到$scope.accuracy
                instrumentType: '=', // 双向绑定器具二级类别
            },
            //模板
            templateUrl: 'views/directive/yunzhiAccuracy.html',
            restrict: 'EA', //指令类型，多为E（元素），A（属性）
            controller: function($scope) {
                $scope.accuracies = []; //初始化所有准确度
                $scope.accuracy = {}; //初始化准确度
                $scope.accuracy.selected = $scope.ngModel; //传值

                // 当用户进行选择时，更新ngModel。
                $scope.updateModel = function(newValue) {
                    $scope.ngModel = newValue;
                };
            },
            link: function postLink(scope) {
                var data = {value: '请选择'};
                // 监听传入的data-appliance是否发生了变化，如果发生了变化，则重新获取准确度等级列表
                scope.$watch('instrumentType', function(newValue) {
                    if (newValue && newValue.id) {
                        //获取用户可见的准确度列表
                        AccuracyService.getAllByInstrumentTypeId(newValue.id, function(datas) {
                            scope.accuracies = datas;
                            scope.accuracies.unshift(data);
                        });
                    } else {
                        scope.accuracies = [data];
                        //隐藏准确度列表，则将 选中的准确度 初始化，并传给V层，供搜索使用。
                        scope.ngModel = scope.accuracy.selected = data;
                    }
                }, true);
            }
        };
    }]);
