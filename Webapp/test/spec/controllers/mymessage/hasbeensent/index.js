'use strict';

describe('Controller: MymessageHasbeensentMymessagehasbeensentindexctrlCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MymessageHasbeensentMymessagehasbeensentindexctrlCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MymessageHasbeensentMymessagehasbeensentindexctrlCtrl = $controller('MymessageHasbeensentMymessagehasbeensentindexctrlCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MymessageHasbeensentMymessagehasbeensentindexctrlCtrl.awesomeThings.length).toBe(3);
  });
});
