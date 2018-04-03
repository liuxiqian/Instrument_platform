'use strict';

describe('Controller: MandatoryIntegratedEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryIntegratedEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryIntegratedEditCtrl = $controller('MandatoryIntegratedEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryIntegratedEditCtrl.awesomeThings.length).toBe(3);
  });
});
