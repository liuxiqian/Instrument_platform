'use strict';

describe('Controller: MymessageInboxEditorCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MymessageInboxEditorCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymessageInboxEditorCtrl = $controller('MymessageInboxEditorCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MymessageInboxEditorCtrl.awesomeThings.length).toBe(3);
  });
});
