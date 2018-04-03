'use strict';

describe('Controller: InstrumentForcedapplyViewindexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedapplyViewindexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedapplyViewindexCtrl = $controller('InstrumentForcedapplyViewindexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedapplyViewindexCtrl.awesomeThings.length).toBe(3);
  });
});
