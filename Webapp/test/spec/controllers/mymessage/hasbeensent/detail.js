'use strict';

describe('Controller: MymessageHasbeensentDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MymessageHasbeensentDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymessageHasbeensentDetailCtrl = $controller('MymessageHasbeensentDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MymessageHasbeensentDetailCtrl.awesomeThings.length).toBe(3);
  });
});
