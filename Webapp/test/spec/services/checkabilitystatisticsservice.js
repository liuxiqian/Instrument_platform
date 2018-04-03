'use strict';

describe('Service: CheckAbilityStatisticsService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var CheckAbilityStatisticsService;
  beforeEach(inject(function (_CheckAbilityStatisticsService_) {
    CheckAbilityStatisticsService = _CheckAbilityStatisticsService_;
  }));

  it('should do something', function () {
    expect(!!CheckAbilityStatisticsService).toBe(true);
  });

});
