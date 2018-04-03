'use strict';

describe('Controller: InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl = $controller('InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedIntegratedquerytimeoutcheckapplyindexCtrl.awesomeThings.length).toBe(3);
  });
});
