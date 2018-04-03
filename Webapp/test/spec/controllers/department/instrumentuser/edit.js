'use strict';

describe('Controller: DepartmentInstrumentuserEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentInstrumentuserEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentInstrumentuserEditCtrl = $controller('DepartmentInstrumentuserEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentInstrumentuserEditCtrl.awesomeThings.length).toBe(3);
  });
});
