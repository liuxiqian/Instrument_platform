'use strict';

describe('Service: forceInstrumentCheckRateService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var forceInstrumentCheckRateService;
  beforeEach(inject(function (_forceInstrumentCheckRateService_) {
    forceInstrumentCheckRateService = _forceInstrumentCheckRateService_;
  }));

  it('should do something', function () {
    expect(!!forceInstrumentCheckRateService).toBe(true);
  });

});
