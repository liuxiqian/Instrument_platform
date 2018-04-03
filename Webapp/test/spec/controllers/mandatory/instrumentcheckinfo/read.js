'use strict';

describe('Controller: MandatoryInstrumentcheckinfoReadCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryInstrumentcheckinfoReadCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryInstrumentcheckinfoReadCtrl = $controller('MandatoryInstrumentcheckinfoReadCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryInstrumentcheckinfoReadCtrl.awesomeThings.length).toBe(3);
  });
});
