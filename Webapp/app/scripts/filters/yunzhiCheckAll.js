'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:yunzhiCheckAll
 * @function
 * @description
 * # yunzhiCheckAll
 * Filter in the webappApp.
 */
angular.module('webappApp')
    .filter('yunzhiCheckAll', function() {
        return function(input, key) {
            if (typeof(key) === 'undefined') {
                key = '_checked';
            }

            angular.forEach(input, function(value) {
                // 如果有属性就不进行过滤。原因是由于input每改变一次，过滤器都会执行一遍操作。
                // 如果已经有了_checked属性，此时还过滤的话，就会造成二次过滤。即input每改变一次，都会立即恢复为原值。
                if (typeof(value[key]) === 'undefined') {
                    value[key] = false;
                }
            });
            
            return input;
        };
    });
