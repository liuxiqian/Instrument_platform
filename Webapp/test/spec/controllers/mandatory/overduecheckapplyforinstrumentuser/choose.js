'use strict';

describe('Controller: MandatoryOverduecheckapplyforinstrumentuserChooseCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryOverduecheckapplyforinstrumentuserChooseCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryOverduecheckapplyforinstrumentuserChooseCtrl = $controller('MandatoryOverduecheckapplyforinstrumentuserChooseCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryOverduecheckapplyforinstrumentuserChooseCtrl.awesomeThings.length).toBe(3);
  });
});
