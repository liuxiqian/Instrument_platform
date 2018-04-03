'use strict';

describe('Controller: MandatoryOverduecheckapplyforinstrumentuserViewCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryOverduecheckapplyforinstrumentuserViewCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryOverduecheckapplyforinstrumentuserViewCtrl = $controller('MandatoryOverduecheckapplyforinstrumentuserViewCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryOverduecheckapplyforinstrumentuserViewCtrl.awesomeThings.length).toBe(3);
  });
});
