'use strict';

describe('Service: MandatoryCheckApplyService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var MandatoryCheckApplyService;
  beforeEach(inject(function (_MandatoryCheckApplyService_) {
    MandatoryCheckApplyService = _MandatoryCheckApplyService_;
  }));

  it('should do something', function () {
    expect(!!MandatoryCheckApplyService).toBe(true);
  });

});
