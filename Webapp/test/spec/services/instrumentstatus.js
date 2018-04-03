'use strict';

describe('Service: instrumentStatus', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var instrumentStatus;
  beforeEach(inject(function (_instrumentStatus_) {
    instrumentStatus = _instrumentStatus_;
  }));

  it('should do something', function () {
    expect(!!instrumentStatus).toBe(true);
  });

});
