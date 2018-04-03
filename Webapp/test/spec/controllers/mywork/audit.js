'use strict';

describe('Controller: MyworkAuditCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkAuditCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkAuditCtrl = $controller('MyworkAuditCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MyworkAuditCtrl.awesomeThings.length).toBe(3);
  });
});
