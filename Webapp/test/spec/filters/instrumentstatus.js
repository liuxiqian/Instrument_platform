'use strict';

describe('Filter: instrumentStatus', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var instrumentStatus;
  beforeEach(inject(function ($filter) {
    instrumentStatus = $filter('instrumentStatus');
  }));

  it('should return the input prefixed with "instrumentStatus filter:"', function () {
    var text = 'angularjs';
    expect(instrumentStatus(text)).toBe('instrumentStatus filter: ' + text);
  });

});
