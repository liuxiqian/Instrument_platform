'use strict';

describe('Filter: MeasureScale', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var MeasureScale;
  beforeEach(inject(function ($filter) {
    MeasureScale = $filter('MeasureScale');
  }));

  it('should return the input prefixed with "MeasureScale filter:"', function () {
    var text = 'angularjs';
    expect(MeasureScale(text)).toBe('MeasureScale filter: ' + text);
  });

});
