'use strict';

describe('Filter: nullDataFilter', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var nullDataFilter;
  beforeEach(inject(function ($filter) {
    nullDataFilter = $filter('nullDataFilter');
  }));

  it('should return the input prefixed with "nullDataFilter filter:"', function () {
    var text = 'angularjs';
    expect(nullDataFilter(text)).toBe('nullDataFilter filter: ' + text);
  });

});
