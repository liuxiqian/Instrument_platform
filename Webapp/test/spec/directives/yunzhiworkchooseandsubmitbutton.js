'use strict';

describe('Directive: yunzhiWorkChooseAndSubmitButton', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-work-choose-and-submit-button></yunzhi-work-choose-and-submit-button>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiWorkChooseAndSubmitButton directive');
  }));
});
