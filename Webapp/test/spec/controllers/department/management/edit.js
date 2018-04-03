'use strict';

describe('Controller: DepartmentManagementEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentManagementEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentManagementEditCtrl = $controller('DepartmentManagementEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentManagementEditCtrl.awesomeThings.length).toBe(3);
  });
});
