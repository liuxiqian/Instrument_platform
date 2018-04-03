'use strict';

describe('Controller: StandardFileCheckdetaileditctrlCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardFileCheckdetaileditctrlCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardFileCheckdetaileditctrlCtrl = $controller('StandardFileCheckdetaileditctrlCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardFileCheckdetaileditctrlCtrl.awesomeThings.length).toBe(3);
  });
});
