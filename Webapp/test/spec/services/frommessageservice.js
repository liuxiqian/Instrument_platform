'use strict';

describe('Service: FromMessageService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var FromMessageService;
  beforeEach(inject(function (_FromMessageService_) {
    FromMessageService = _FromMessageService_;
  }));

  it('should do something', function () {
    expect(!!FromMessageService).toBe(true);
  });

});
