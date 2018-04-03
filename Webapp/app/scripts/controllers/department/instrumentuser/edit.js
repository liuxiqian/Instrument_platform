'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentInstrumentuserEditCtrl
 * @description
 * # DepartmentInstrumentuserEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('DepartmentInstrumentuserEditCtrl', function ($scope, $controller) {
      //继承DepartmentEditCtrl
      $controller('DepartmentEditCtrl', {$scope: $scope});
  });
