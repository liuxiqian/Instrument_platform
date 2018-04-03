'use strict';

describe('Controller: MandatoryOverduecheckapplyforinstrumentuserAuditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MandatoryOverduecheckapplyforinstrumentuserAuditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MandatoryOverduecheckapplyforinstrumentuserAuditCtrl = $controller('MandatoryOverduecheckapplyforinstrumentuserAuditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MandatoryOverduecheckapplyforinstrumentuserAuditCtrl.awesomeThings.length).toBe(3);
  });
});
