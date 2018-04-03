'use strict';

describe('Controller: InstrumentForcedEditcontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedEditcontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedEditcontrollerCtrl = $controller('InstrumentForcedEditcontrollerCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedEditcontrollerCtrl.awesomeThings.length).toBe(3);
  });
});
