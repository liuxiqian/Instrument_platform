'use strict';

describe('Controller: ForceinstrumentstatisticsCheckabilityIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var ForceinstrumentstatisticsCheckabilityIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ForceinstrumentstatisticsCheckabilityIndexCtrl = $controller('ForceinstrumentstatisticsCheckabilityIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ForceinstrumentstatisticsCheckabilityIndexCtrl.awesomeThings.length).toBe(3);
  });
});
