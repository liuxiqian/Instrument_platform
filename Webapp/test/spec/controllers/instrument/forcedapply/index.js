'use strict';

describe('Controller: InstrumentForcedapplyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedapplyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedapplyIndexCtrl = $controller('InstrumentForcedapplyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedapplyIndexCtrl.awesomeThings.length).toBe(3);
  });
});
