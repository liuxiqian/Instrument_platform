'use strict';

describe('Controller: MandatoryCheckapplyfortechnicalinstitutionIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryCheckapplyfortechnicalinstitutionIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryCheckapplyfortechnicalinstitutionIndexCtrl = $controller('MandatoryCheckapplyfortechnicalinstitutionIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryCheckapplyfortechnicalinstitutionIndexCtrl.awesomeThings.length).toBe(3);
  });
});
