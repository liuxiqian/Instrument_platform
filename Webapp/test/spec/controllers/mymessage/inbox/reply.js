'use strict';

describe('Controller: MymessageInboxReplyCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MymessageInboxReplyCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymessageInboxReplyCtrl = $controller('MymessageInboxReplyCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MymessageInboxReplyCtrl.awesomeThings.length).toBe(3);
  });
});
