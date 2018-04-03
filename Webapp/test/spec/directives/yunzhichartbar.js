'use strict';

describe('Directive: yunzhiChartBar', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-chart-bar></yunzhi-chart-bar>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiChartBar directive');
  }));
});
