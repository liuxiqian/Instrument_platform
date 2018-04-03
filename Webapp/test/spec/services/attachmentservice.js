'use strict';

describe('Service: attachmentService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var attachmentService;
  beforeEach(inject(function (_attachmentService_) {
    attachmentService = _attachmentService_;
  }));

  it('should do something', function () {
    expect(!!attachmentService).toBe(true);
  });

});
