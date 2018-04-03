'use strict';

describe('Controller: StandardwarnctrlCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var StandardwarnctrlCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    StandardwarnctrlCtrl = $controller('StandardwarnctrlCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(StandardwarnctrlCtrl.awesomeThings.length).toBe(3);
  });
});
