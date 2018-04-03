'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentManagementEditCtrl
 * @description
 * # DepartmentManagementEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('DepartmentManagementEditCtrl', function ($scope, $controller) {
        //继承department
      $controller('DepartmentEditCtrl', {$scope: $scope});
  });
