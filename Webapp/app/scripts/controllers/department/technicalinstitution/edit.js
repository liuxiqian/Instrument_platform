'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentTechnicalinstitutionEditCtrl
 * @description
 * # DepartmentTechnicalinstitutionEditCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('DepartmentTechnicalinstitutionEditCtrl', function ($scope, $controller) {
      //继承DepartmentEditCtrl
      $controller('DepartmentEditCtrl', {$scope: $scope});
  });
