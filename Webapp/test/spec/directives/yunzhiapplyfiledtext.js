'use strict';

describe('Directive: yunzhiApplyFiledText', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-apply-filed-text></yunzhi-apply-filed-text>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiApplyFiledText directive');
  }));
});
