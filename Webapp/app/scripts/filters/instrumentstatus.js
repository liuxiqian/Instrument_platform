'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:instrumentStatus
 * @function
 * @description
 * # instrumentStatus
 * Filter in the webappApp.
 * 器具状态
 */
angular.module('webappApp')
    .filter('instrumentStatus', function(instrumentStatus) {
        return function(input) {
            // 初始化
        // input = parseInt(input);
        var style = '';
        var status = instrumentStatus;
        var outputString = '';
        
        // 循环找状态
        angular.forEach(status, function (value) {
            if (input.status === value.value) {
                outputString = value.description;
                style = value.style;
            }
        });
        
        // 如果未找到，则返回未知
        if (outputString === '') {
            outputString = '未知';
            style = 'danger';
        }


        //返回正确的状态
        return '<span class="badge badge-' + style +'">' + outputString + '</span>';
        };
    });
