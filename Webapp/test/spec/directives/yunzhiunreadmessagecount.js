'use strict';

describe('Directive: yunzhiUnReadMessageCount', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-un-read-message-count></yunzhi-un-read-message-count>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiUnReadMessageCount directive');
  }));
});
