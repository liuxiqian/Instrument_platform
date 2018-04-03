'use strict';

describe('Filter: yunzhiCheckAll', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var yunzhiCheckAll;
  beforeEach(inject(function ($filter) {
    yunzhiCheckAll = $filter('yunzhiCheckAll');
  }));

  it('should return the input prefixed with "yunzhiCheckAll filter:"', function () {
    var text = 'angularjs';
    expect(yunzhiCheckAll(text)).toBe('yunzhiCheckAll filter: ' + text);
  });

});
