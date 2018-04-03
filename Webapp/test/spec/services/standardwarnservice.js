'use strict';

describe('Service: StandardWarnService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var StandardWarnService;
  beforeEach(inject(function (_StandardWarnService_) {
    StandardWarnService = _StandardWarnService_;
  }));

  it('should do something', function () {
    expect(!!StandardWarnService).toBe(true);
  });

});
