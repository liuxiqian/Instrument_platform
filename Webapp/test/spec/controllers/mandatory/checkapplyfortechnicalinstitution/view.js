'use strict';

describe('Controller: MandatoryCheckapplyfortechnicalinstitutionViewCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryCheckapplyfortechnicalinstitutionViewCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryCheckapplyfortechnicalinstitutionViewCtrl = $controller('MandatoryCheckapplyfortechnicalinstitutionViewCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryCheckapplyfortechnicalinstitutionViewCtrl.awesomeThings.length).toBe(3);
  });
});
