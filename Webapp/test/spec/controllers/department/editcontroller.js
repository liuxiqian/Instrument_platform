'use strict';

describe('Controller: DepartmentEditcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentEditcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentEditcontrollerCtrl = $controller('DepartmentEditcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentEditcontrollerCtrl.awesomeThings.length).toBe(3);
  });
});
