'use strict';

describe('Controller: MymessageInboxDetailCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MymessageInboxDetailCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymessageInboxDetailCtrl = $controller('MymessageInboxDetailCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MymessageInboxDetailCtrl.awesomeThings.length).toBe(3);
  });
});
