'use strict';

describe('Controller: InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl = $controller('InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(InstrumentForcedTechnologydepartmentintegratedqueryindexctrlCtrl.awesomeThings.length).toBe(3);
  });
});
