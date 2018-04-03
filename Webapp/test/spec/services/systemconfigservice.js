'use strict';

describe('Service: SystemConfigService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var SystemConfigService;
  beforeEach(inject(function (_SystemConfigService_) {
    SystemConfigService = _SystemConfigService_;
  }));

  it('should do something', function () {
    expect(!!SystemConfigService).toBe(true);
  });

});
