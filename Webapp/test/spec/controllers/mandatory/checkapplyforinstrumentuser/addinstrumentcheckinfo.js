'use strict';

describe('Controller: MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl = $controller('MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryCheckapplyforinstrumentuserAddinstrumentcheckinfoCtrl.awesomeThings.length).toBe(3);
  });
});
