'use strict';

describe('Service: StandardAlarmService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var StandardAlarmService;
  beforeEach(inject(function (_StandardAlarmService_) {
    StandardAlarmService = _StandardAlarmService_;
  }));

  it('should do something', function () {
    expect(!!StandardAlarmService).toBe(true);
  });

});
