'use strict';

describe('Controller: InstrumentForcedapplyAuditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedapplyAuditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedapplyAuditCtrl = $controller('InstrumentForcedapplyAuditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedapplyAuditCtrl.awesomeThings.length).toBe(3);
  });
});
