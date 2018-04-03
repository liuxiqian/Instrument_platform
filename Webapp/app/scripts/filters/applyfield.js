'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:ApplyField
 * @function
 * @description		字段类型中是否显示将true/false显示为是/否
 * @author zhangjiahao
 */
angular.module('webappApp')
    .filter('ApplyField', function () {
        return function(input) {
            if (input === true) {
                return "是";
            } else {
                return "否";
            }
        };
    });
