'use strict';

describe('Directive: yunzhiPurpose', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  // var element;
   var scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  // it('should make hidden element visible', inject(function ($compile) {
  //   element = angular.element('<yunzhi-purpose></yunzhi-purpose>');
  //   element = $compile(element)(scope);
  //   expect(element.text()).toBe('this is the yunzhiPurpose directive');
  // }));
});
