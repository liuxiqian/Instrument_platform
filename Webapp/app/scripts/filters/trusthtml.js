'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:trustHtml
 * @function
 * @description 用于html标签的转义
 * # trustHtml
 * Filter in the webappApp.
 */
angular.module('webappApp')
  .filter('trustHtml', function ($sce) {
    return function (input) {
      return $sce.trustAsHtml(input);
    };
  });
