'use strict';

describe('Controller: DashboardhandlingCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DashboardhandlingCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DashboardhandlingCtrl = $controller('DashboardhandlingCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DashboardhandlingCtrl.awesomeThings.length).toBe(3);
  });
});
