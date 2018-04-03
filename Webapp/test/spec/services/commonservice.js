'use strict';

describe('Service: CommonService', function () {

  // load the service's module
  beforeEach(module('webappApp'));

  // instantiate service
  var CommonService;
  beforeEach(inject(function (_CommonService_) {
    CommonService = _CommonService_;
  }));

  it('查找数据中的值', function () {
      var array = [
          {id: 1, name: 'zhangsan'},
          {id: 2, name: 'lisi'}
      ];
      var result = CommonService.findByIdAndArray(1, array);
      expect(result.name).toBe('zhangsan');

      result = CommonService.findByIdAndArray(2, array);
      expect(result.name).toBe('lisi');
  });

});
