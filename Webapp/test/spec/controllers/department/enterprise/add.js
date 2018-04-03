'use strict';

describe('Controller: DepartmentEnterpriseAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentEnterpriseAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentEnterpriseAddCtrl = $controller('DepartmentEnterpriseAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentEnterpriseAddCtrl.awesomeThings.length).toBe(3);
  });
});
