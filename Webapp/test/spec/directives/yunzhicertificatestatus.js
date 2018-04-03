'use strict';

describe('Directive: yunzhiCertificateStatus', function () {

  // load the directive's module
  beforeEach(module('webappApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<yunzhi-certificate-status></yunzhi-certificate-status>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the yunzhiCertificateStatus directive');
  }));
});
