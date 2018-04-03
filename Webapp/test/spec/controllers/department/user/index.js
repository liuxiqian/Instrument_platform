'use strict';

describe('Controller: DepartmentUserIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentUserIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentUserIndexCtrl = $controller('DepartmentUserIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentUserIndexCtrl.awesomeThings.length).toBe(3);
  });
});
