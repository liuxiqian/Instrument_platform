'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:yunzhiTureOrFalse
 * @function
 * @description
 * # yunzhiTureOrFalse
 * Filter in the webappApp.
 */
angular.module('webappApp')
    .filter('yunzhiTrueOrFalse', function() {
        return function(input) {
            if (input === true) {
                return '是';
            } else if (input === false) {
                return '否';
            } else {
                return '-';
            }
        };
    });
