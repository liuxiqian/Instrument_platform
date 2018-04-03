'use strict';

describe('Controller: DepartmentEnterpriseEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentEnterpriseEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentEnterpriseEditCtrl = $controller('DepartmentEnterpriseEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentEnterpriseEditCtrl.awesomeThings.length).toBe(3);
  });
});
