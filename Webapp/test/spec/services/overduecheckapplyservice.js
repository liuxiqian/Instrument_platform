'use strict';

describe('Service: OverdueCheckApplyService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var OverdueCheckApplyService;
  beforeEach(inject(function (_OverdueCheckApplyService_) {
    OverdueCheckApplyService = _OverdueCheckApplyService_;
  }));

  it('should do something', function () {
    expect(!!OverdueCheckApplyService).toBe(true);
  });

});
