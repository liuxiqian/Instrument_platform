'use strict';

describe('Controller: InstrumentForcedapplyEditindexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedapplyEditindexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedapplyEditindexCtrl = $controller('InstrumentForcedapplyEditindexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedapplyEditindexCtrl.awesomeThings.length).toBe(3);
  });
});
