'use strict';

describe('Controller: InstrumentForcedapplyAddindexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedapplyAddindexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedapplyAddindexCtrl = $controller('InstrumentForcedapplyAddindexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedapplyAddindexCtrl.awesomeThings.length).toBe(3);
  });
});
