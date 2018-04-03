'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiBackButton
 * @description
 * # yunzhiBackButton
 */
angular.module('webappApp')
    .directive('yunzhiBackButton', function(CommonService) {
        return {
           replace: true,
            scope: {
                theme: '@?'     // 主题
            },
            restrict: 'AE',
            // 模板
            templateUrl: 'views/directive/yunzhiBackButton.html',
            link: function postLink(scope) {
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
