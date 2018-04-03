'use strict';

describe('Controller: StandardAuthorizationEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardAuthorizationEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardAuthorizationEditCtrl = $controller('StandardAuthorizationEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardAuthorizationEditCtrl.awesomeThings.length).toBe(3);
  });
});
