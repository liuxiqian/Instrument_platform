'use strict';

describe('Service: InstrumentGenerativeDepartment', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var InstrumentGenerativeDepartment;
  beforeEach(inject(function (_InstrumentGenerativeDepartment_) {
    InstrumentGenerativeDepartment = _InstrumentGenerativeDepartment_;
  }));

  it('should do something', function () {
    expect(!!InstrumentGenerativeDepartment).toBe(true);
  });

});
