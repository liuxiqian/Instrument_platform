'use strict';

describe('Controller: DepartmentInstrumentuserAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentInstrumentuserAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentInstrumentuserAddCtrl = $controller('DepartmentInstrumentuserAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentInstrumentuserAddCtrl.awesomeThings.length).toBe(3);
  });
});
