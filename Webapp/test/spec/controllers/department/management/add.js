'use strict';

describe('Controller: DepartmentManagementAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentManagementAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentManagementAddCtrl = $controller('DepartmentManagementAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentManagementAddCtrl.awesomeThings.length).toBe(3);
  });
});
