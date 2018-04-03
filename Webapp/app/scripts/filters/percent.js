'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:percent
 * @function
 * @description
 * # percent
 * Filter in the webappApp.
 * 百分比过滤器
 * zhangxishuo
 * 适用于强检器具统计管理中对百分比的转换
 */
angular.module('webappApp')
    .filter('percent', function() {
        return function(input) {
            if (isNaN(input)) {
                // 如果是NaN的话，说明计算错误
                // 百分比中用的是除法运算，只可能是0/0
                // 此种情况返回--，表示该受检率无意义
                return '--';
            } else {
                // 将原小数乘以10000，保留最接近的整数，再除以100，实现四舍五入保留两位小数
                var result = Math.round(input * 10000) / 100;
                // 添加百分号
                return result + '%';
            }
        };
    });
