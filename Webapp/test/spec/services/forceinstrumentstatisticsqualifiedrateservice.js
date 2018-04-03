'use strict';

describe('Service: forceInstrumentStatisticsQualifiedrateService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var forceInstrumentStatisticsQualifiedrateService;
  beforeEach(inject(function (_forceInstrumentStatisticsQualifiedrateService_) {
    forceInstrumentStatisticsQualifiedrateService = _forceInstrumentStatisticsQualifiedrateService_;
  }));

  it('should do something', function () {
    expect(!!forceInstrumentStatisticsQualifiedrateService).toBe(true);
  });

});
