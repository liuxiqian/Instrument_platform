'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryInstrumentcheckinfoReadCtrl
 * @description
 * # MandatoryInstrumentcheckinfoReadCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
    .controller('MandatoryInstrumentcheckinfoReadCtrl', ['$scope', 
                                                        'InstrumentCheckInfoService',
                                                        '$stateParams',
                                                        'CommonService',
                                                        function($scope, InstrumentCheckInfoService, $stateParams, CommonService) {
        var self = this;

        //获取路由参数
        self.mandatoryInstrumentId = $stateParams.id ? parseInt($stateParams.id) : 0;

        //从后台获取相应的参数
        InstrumentCheckInfoService.getOneByMandatoryInstrumentId(self.mandatoryInstrumentId, function(data) {
        	$scope.data = data;
        	console.log(data);
        });

        //返回功能
        self.back = CommonService.back;

        //统一暴露方法
        $scope.back = self.back;
    }]);
