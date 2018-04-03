'use strict';

describe('Controller: ForceinstrumentstatisticsCheckrateIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var ForceinstrumentstatisticsCheckrateIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ForceinstrumentstatisticsCheckrateIndexCtrl = $controller('ForceinstrumentstatisticsCheckrateIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ForceinstrumentstatisticsCheckrateIndexCtrl.awesomeThings.length).toBe(3);
  });
});
