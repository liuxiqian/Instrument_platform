'use strict';

describe('Controller: MyworkViewCtrl', function () {

  // load the controller's module
  beforeEach(module('webappApp'));

  var MyworkViewCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MyworkViewCtrl = $controller('MyworkViewCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(MyworkViewCtrl.awesomeThings.length).toBe(3);
  });
});
