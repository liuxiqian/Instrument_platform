'use strict';

describe('Directive: yunzhiExpiredNotChecked', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-expired-not-checked></yunzhi-expired-not-checked>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiExpiredNotChecked directive');
  }));
});
