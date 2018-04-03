'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiWorkChooseAndSubmitButton
 * @工作流选择结点以及提交按钮
 * # yunzhiWorkChooseAndSubmitButton
 */
angular.module('webappApp')
    .directive('yunzhiWorkChooseAndSubmitButton', function(CommonService) {
        return {
            templateUrl: 'views/directive/yunzhiWorkChooseAndSubmitButton.html',
            restrict: 'E',
            scope: {
            	work: '=',
            	auditType: '=',
            	nextWork: '=',
            	showBack: '=',
            	ngSubmit: '='
            },
            link: function postLink(scope) {
                scope.back = CommonService.back;
            }
        };
    });
