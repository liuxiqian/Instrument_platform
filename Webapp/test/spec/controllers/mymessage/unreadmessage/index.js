'use strict';

describe('Controller: MymessageUnreadmessageIndexCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MymessageUnreadmessageIndexCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymessageUnreadmessageIndexCtrl = $controller('MymessageUnreadmessageIndexCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MymessageUnreadmessageIndexCtrl.awesomeThings.length).toBe(3);
  });
});
