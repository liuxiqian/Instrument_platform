'use strict';

describe('Controller: DashboardunhandlCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var DashboardunhandlCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DashboardunhandlCtrl = $controller('DashboardunhandlCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(DashboardunhandlCtrl.awesomeThings.length).toBe(3);
  });
});
