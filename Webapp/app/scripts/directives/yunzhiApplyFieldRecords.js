'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiApplyFields
 * @description 某个申请对应申请字段信息(遍历)
 * # yunzhiApplyFields
 */
angular.module('webappApp')
    .directive('yunzhiApplyFieldRecords', function() {
        return {
            templateUrl: 'views/directive/yunzhiApplyFieldRecords.html',
            restrict: 'EA',
            scope: {
                work: '='
            }
        };
    });
