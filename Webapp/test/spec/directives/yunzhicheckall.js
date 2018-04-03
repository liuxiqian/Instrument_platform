'use strict';

describe('Directive: yunzhiCheckAll', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-check-all></yunzhi-check-all>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiCheckAll directive');
  }));
});
