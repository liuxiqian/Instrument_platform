'use strict';

describe('Directive: yunzhiIsConfirmedByTechnologyDepartment', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-is-confirmed-by-technology-department></yunzhi-is-confirmed-by-technology-department>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiIsConfirmedByTechnologyDepartment directive');
  }));
});
