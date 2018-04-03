'use strict';

describe('Controller: ForceinstrumentstatisticsQualifiedrateIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var ForceinstrumentstatisticsQualifiedrateIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ForceinstrumentstatisticsQualifiedrateIndexCtrl = $controller('ForceinstrumentstatisticsQualifiedrateIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ForceinstrumentstatisticsQualifiedrateIndexCtrl.awesomeThings.length).toBe(3);
  });
});
