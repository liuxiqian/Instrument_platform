'use strict';

describe('Controller: DepartmentTechnicalinstitutionIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DepartmentTechnicalinstitutionIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DepartmentTechnicalinstitutionIndexCtrl = $controller('DepartmentTechnicalinstitutionIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DepartmentTechnicalinstitutionIndexCtrl.awesomeThings.length).toBe(3);
  });
});
