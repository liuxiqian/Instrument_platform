'use strict';

describe('Service: WorkflowNodeAndApplyFieldAccessService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var WorkflowNodeAndApplyFieldAccessService;
  beforeEach(inject(function (_WorkflowNodeAndApplyFieldAccessService_) {
    WorkflowNodeAndApplyFieldAccessService = _WorkflowNodeAndApplyFieldAccessService_;
  }));

  it('should do something', function () {
    expect(!!WorkflowNodeAndApplyFieldAccessService).toBe(true);
  });

});
