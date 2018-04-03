'use strict';

describe('Controller: StandardFileEditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFileEditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFileEditCtrl = $controller('StandardFileEditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardFileEditCtrl.awesomeThings.length).toBe(3);
  });
});
