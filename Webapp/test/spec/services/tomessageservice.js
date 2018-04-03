'use strict';

describe('Service: toMessageService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var toMessageService;
  beforeEach(inject(function (_toMessageService_) {
    toMessageService = _toMessageService_;
  }));

  it('should do something', function () {
    expect(!!toMessageService).toBe(true);
  });

});
