'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhioverdue
 * @description 是否超期未检
 * # yunzhioverdue
 */
angular.module('webappApp')
  .directive('yunzhiOverdue', function () {
    return {
      scope: {
          ngModel: '='
      },
      templateUrl: 'views/directive/yunzhiOverdue.html',
      restrict: 'EA'
    };
  });
