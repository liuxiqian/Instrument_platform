'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatoryCheckapplyforinstrumentuserAddCtrl
 * @description
 * # MandatoryCheckapplyforinstrumentuserAddCtrl
 * Controller of the webappApp
 * 强检器具检定管理 - 检定申请 - 新增检定申请
 * panjie
 */
angular.module('webappApp')
    .controller('MandatoryCheckapplyforinstrumentuserAddCtrl',
        function($scope,
            $stateParams,
            $state,
            UserServer,
            CommonService,
            checkApplyForInstrumentUserService) {
            var self = this;
            var overrideInit = typeof($scope.overrideInit) === 'undefined' ? false : $scope.overrideInit;

            if (overrideInit) {
                self.init = function() {};
            } else {
                self.init = function() {
                    $scope.data = {};
                    self.initScopeData();
                    self.validate();
                    UserServer.getCurrentLoginUser(function(user) {
                        $scope.data.applyUser = user;
                    });
                };
            }

            // 系统较验
            self.validate = function() {

            };

            // 初始化scope数据
            self.initScopeData = function() {
                var date = new Date();
                $scope.data = {
                    applyDate: date,
                    checkPlace: '', // 检定地点
                    remarks: '', // 备注
                    acceptTechnicalInstitutionDepartment: undefined, // 检定技术机构
                    applyUser: undefined, // 申请用户
                    mandatoryInstrumentSet: $stateParams.mandatoryInstrumentSet // 强检器具
                };
            };

            self.submit = function() {
                // 判断用户是否选择了送检的器具
                if ($scope.data.mandatoryInstrumentSet.length !== 0) {
                    checkApplyForInstrumentUserService.save($scope.data)
                        .then(function success(){
                            CommonService.success(undefined, undefined, function() {
                                $state.go('mandatory.checkApplyForInstrumentUser', {}, { reload: true });
                            });
                        }, function error(response){
                            CommonService.httpError(response);
                        });
                } else {
                    CommonService.error('请选择器具');
                    $state.reload();
                }
            };

            /**
             * 添加器具
             */
            self.addInstrument = function() {
                $state.transitionTo('mandatory.checkApplyForInstrumentUser.Add.choose', { mandatoryInstrumentSet: $scope.data.mandatoryInstrumentSet });
            };

            self.init();
            $scope.addInstrument = self.addInstrument;
            $scope.submit = self.submit;
        });
