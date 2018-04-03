'use strict';

describe('Service: backedReasonService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var backedReasonService;
  beforeEach(inject(function (_backedReasonService_) {
    backedReasonService = _backedReasonService_;
  }));

  it('should do something', function () {
    expect(!!backedReasonService).toBe(true);
  });

});
