'use strict';

describe('Controller: StandardalarmCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardalarmCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardalarmCtrl = $controller('StandardalarmCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardalarmCtrl.awesomeThings.length).toBe(3);
  });
});
