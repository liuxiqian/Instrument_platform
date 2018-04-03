'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiYear
 * @description 年度指令
 * # yunzhiYear
 */
angular.module('webappApp')
    .directive('yunzhiYear', function() {
        return {
            // 独立scope，使各个指令间不互相影响
            scope: {
                // 将指令中的year属性，双向绑定到scope.year
                ngModel: '='
            },
            //模板
            templateUrl: 'views/directive/yunzhiYear.html',
            restrict: 'EA',
            controller: function($scope) {
                $scope.years = []; // 初始化所有年度
                $scope.year = {}; // 初始化年度

                // 获取用户可见的年度列表
                var beginYear = 2017; //起始年份
                var today = new Date(); //获取当前日期
                var currentYear = today.getFullYear(); //获取当前年份

                var initObject = {name: '请选择', value: undefined};
                $scope.years.push(initObject);
                $scope.year.selected = initObject;
                //循环到当前年份
                for (var i = beginYear; i <= currentYear; i++) {
                    var year = {
                        name: i,
                        value: i
                    };
                    if (i === $scope.ngModel) {
                        $scope.year.selected = year;
                    }
                    //存入年度数组里面，供V层作为循环使用
                    $scope.years.push(year);
                }
            },
            link: function postLink(scope) {
                // 监视年度是否发生变化。如果发生变化，则重置ngModel的值。此时，利用双向数据绑定。将值传回V层
                scope.$watch('year', function(newValue) {
                    scope.ngModel = newValue.selected.value;
                }, true);
            }
        };
    });
