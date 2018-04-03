'use strict';

describe('Controller: DepartmentUserAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentUserAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentUserAddCtrl = $controller('DepartmentUserAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentUserAddCtrl.awesomeThings.length).toBe(3);
  });
});
