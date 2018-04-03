'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiBack
 * @description 后退按钮
 * # yunzhiBack
 */
angular.module('webappApp')
    .directive('yunzhiBack', function(CommonService) {
        return {
            replace: true,
            scope: {
                theme: '@?'		// 主题
            },
            restrict: 'AE',
            // 模板
            template: '<div ng-include="getContentUrl()"></div>',
            link: function postLink(scope) {
            	if (typeof(scope.theme) === 'undefined') {
            		scope.theme = '';
            	}

                scope.getContentUrl = function() {
                	return 'views/directive/yunzhiBack' + scope.theme + '.html';
	            };

                scope.back = CommonService.back;
                // 当路由状态的个数大于1时，表示可以后退。否则隐藏后退按钮
                scope.showBack = function() {
                    if (CommonService.getStates().length > 1) {
                        return true;
                    } else {
                        return false;
                    }
                };
            }
        };
    });
