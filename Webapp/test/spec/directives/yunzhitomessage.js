'use strict';

describe('Directive: yunzhiToMessage', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-to-message></yunzhi-to-message>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiToMessage directive');
  }));
});
