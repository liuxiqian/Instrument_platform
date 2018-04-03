'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentEnterpriseEditCtrl
 * @description
 * # DepartmentEnterpriseEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('DepartmentEnterpriseEditCtrl', ['$scope', '$controller', function ($scope, $controller) {
      //继承DepartmentEditCtrl
      $controller('DepartmentEditCtrl', {$scope: $scope});

  }]);
