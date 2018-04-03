'use strict';

describe('Controller: DepartmentAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentAddCtrl = $controller('DepartmentAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentAddCtrl.awesomeThings.length).toBe(3);
  });
});
