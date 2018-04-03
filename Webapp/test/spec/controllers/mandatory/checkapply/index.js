'use strict';

describe('Controller: MandatoryCheckapplyIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryCheckapplyIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryCheckapplyIndexCtrl = $controller('MandatoryCheckapplyIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryCheckapplyIndexCtrl.awesomeThings.length).toBe(3);
  });
});
