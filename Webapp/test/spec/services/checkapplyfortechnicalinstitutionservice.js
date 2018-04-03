'use strict';

describe('Service: checkApplyForTechnicalInstitutionService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var checkApplyForTechnicalInstitutionService;
  beforeEach(inject(function (_checkApplyForTechnicalInstitutionService_) {
    checkApplyForTechnicalInstitutionService = _checkApplyForTechnicalInstitutionService_;
  }));

  it('should do something', function () {
    expect(!!checkApplyForTechnicalInstitutionService).toBe(true);
  });

});
