'use strict';

describe('Controller: MandatoryOverduecheckapplyforinstrumentuserAddCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryOverduecheckapplyforinstrumentuserAddCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryOverduecheckapplyforinstrumentuserAddCtrl = $controller('MandatoryOverduecheckapplyforinstrumentuserAddCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryOverduecheckapplyforinstrumentuserAddCtrl.awesomeThings.length).toBe(3);
  });
});
