'use strict';

describe('Controller: MandatoryCheckapplyforinstrumentuserAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryCheckapplyforinstrumentuserAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryCheckapplyforinstrumentuserAddCtrl = $controller('MandatoryCheckapplyforinstrumentuserAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryCheckapplyforinstrumentuserAddCtrl.awesomeThings.length).toBe(3);
  });
});
