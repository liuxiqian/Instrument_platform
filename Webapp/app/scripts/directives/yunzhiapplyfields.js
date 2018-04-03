'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiApplyFields
 * @description
 * # yunzhiApplyFields
 */
angular.module('webappApp')
    .directive('yunzhiApplyFields', function() {
        return {
            templateUrl: 'views/directive/yunzhiApplyFields.html',
            restrict: 'EA',
            scope: {
                work: '='
            }
        };
    });
