'use strict';

describe('Filter: yunzhiTureOrFalse', function () {

  // load the filter's module
  beforeEach(module('webappApp'));

  // initialize a new instance of the filter before each test
  var yunzhiTureOrFalse;
  beforeEach(inject(function ($filter) {
    yunzhiTureOrFalse = $filter('yunzhiTureOrFalse');
  }));

  it('should return the input prefixed with "yunzhiTureOrFalse filter:"', function () {
    var text = 'angularjs';
    expect(yunzhiTureOrFalse(text)).toBe('yunzhiTureOrFalse filter: ' + text);
  });

});
