'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiApplyField
 * @description
 * # yunzhiApplyField
 */
angular.module('webappApp')
    .directive('yunzhiApplyField', function() {
        return {
            restrict: 'EA',
            scope: {
                ngModel: '=',
                work: '='
            },
            template: '<div ng-include="getContentUrl()"></div>',
            link: function postLink(scope) {
                console.log('按申请字段不同的类型，分别调用不同的V层');
                scope.getContentUrl = function() {
                    return 'views/directive/yunzhiApplyField' + scope.ngModel.type.capitalizeFirstLetter() + '.html';
                };
            }
        };
    });
