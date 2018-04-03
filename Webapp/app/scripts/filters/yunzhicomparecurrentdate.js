'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:yunzhiCompareCurrentDate
 * @function
 * @description 获取传入日期和当前日期相差的天数
 * # yunzhiCompareCurrentDate
 * Filter in the webappApp.
 */
angular.module('webappApp')
    .filter('yunzhiCompareCurrentDate', function ($filter, CommonService) {
        var self = this;
        self.computerDaysBetween = function(input) {
            // 获取当前日期
            var currentDate = $filter('date')(new Date(), 'yyyy-MM-dd');
            // 判断是否获取到当前日期
            if (typeof currentDate !== 'undefined') {
                return CommonService.daysBetween(input, currentDate);
            }
        };
        return function (input) {
            return self.computerDaysBetween(input);
        };
    });
