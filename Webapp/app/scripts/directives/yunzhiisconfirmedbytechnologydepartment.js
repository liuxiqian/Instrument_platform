'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiIsConfirmedByTechnologyDepartment
 * @description 是否已被技术机构确认
 * # yunzhiIsConfirmedByTechnologyDepartment
 */
angular.module('webappApp')
  .directive('yunzhiIsConfirmedByTechnologyDepartment', function () {
      return {
          scope: {
              ngModel: '='
          },
          templateUrl: 'views/directive/yunzhiIsConfirmedByTechnologyDepartment.html',
          restrict: 'EA',
          link: function postLink(scope) {
              if (typeof(scope.ngModel) === 'undefined' || (scope.ngModel !== '0' && scope.ngModel !== '1')) {
                  scope.ngModel = '-1';
              }
          }
      };
  });
