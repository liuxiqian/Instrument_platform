'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:yunzhiOverdueRemainDay
 * @function
 * @description
 * # yunzhiOverdueRemainDay
 * 超期剩余日期（剩余多少天就超期了）
 * panjie
 */
angular.module('webappApp')
    .filter('yunzhiOverdueRemainDay', function ($filter) {
        var self = this;
        self.styles = [
            'info', 'warning', 'danger'
        ];

        /**
         * 获取显示的样式
         * @param day 剩余过期天数
         * @returns {string}
         * panjie
         */
        self.getStyleByRemainDay = function(day) {
            var index;
            if (day > 15) {
                index = 0;
            } else if (day > 7) {
                index = 1;
            } else {
                index = 2;
            }
            return self.styles[index];
        };

        /**
         * 输出
         * @param input 输入
         * @returns {string}
         */
        self.output = function (input) {
            var remainDays = $filter('yunzhiCompareCurrentDate')(input);

            return '<span class="badge badge-' + self.getStyleByRemainDay(remainDays) +'">' + remainDays + '</span>';
        };

        return function (input) {
            return self.output(input);
        };
    });
