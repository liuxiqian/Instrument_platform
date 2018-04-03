'use strict';

describe('Filter: yunzhiOverdueRemainDay', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var yunzhiOverdueRemainDay;
  beforeEach(inject(function ($filter) {
    yunzhiOverdueRemainDay = $filter('yunzhiOverdueRemainDay');
  }));

  it('should return the input prefixed with "yunzhiOverdueRemainDay filter:"', function () {
    var text = 'angularjs';
    expect(yunzhiOverdueRemainDay(text)).toBe('yunzhiOverdueRemainDay filter: ' + text);
  });

});
