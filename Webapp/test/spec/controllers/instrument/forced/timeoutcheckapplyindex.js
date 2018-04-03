'use strict';

describe('Controller: InstrumentForcedTimeoutcheckapplyindexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedTimeoutcheckapplyindexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedTimeoutcheckapplyindexCtrl = $controller('InstrumentForcedTimeoutcheckapplyindexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedTimeoutcheckapplyindexCtrl.awesomeThings.length).toBe(3);
  });
});
