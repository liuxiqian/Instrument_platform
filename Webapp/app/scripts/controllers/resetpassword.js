'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:ResetpasswordCtrl
 * @description
 * # ResetpasswordCtrl
 * Controller of the webappApp
 * 重置密码
 */
angular.module('webappApp')
    .controller('ResetpasswordCtrl',
        function ($scope, $timeout, UserServer, CommonService, $state) {
            var self = this;
            self.init = function () {
                $scope.data = {
                    username: '',
                    mobile: ''
                };

                // 手机号码验证
                $scope.regexMobile = '^0?1[0-9]{10}$';
                $scope.isError = false;
                $scope.submit = self.submit;
                $scope.resetPasswordForm = {$submitted: false};
            };

            self.submit = function () {
                UserServer.resetPasswordByUsernameAndMobile($scope.data.username, $scope.data.mobile)
                    .then(function success() {
                        CommonService.success('操作成功', '新的密码已发送到' + $scope.data.mobile + '，请稍后使用新密码登录系统', function() {
                            $state.go('login');
                        });
                    }, function error(response) {
                        var status = response.status;
                        if (status === 403 || status === 406) {
                            self.setError();
                        } else {
                            CommonService.httpError(response);
                            self.init();
                        }
                    });
            };

            // 发生了错误
            self.setError = function () {
                $scope.isError = true;
                self.countDown(60);
            };

            // 倒计时
            self.countDown = function (time) {
                $scope.leftSeconds = time;
                $timeout(function () {
                    time--;
                    if (time > 0) {
                        self.countDown(time);
                    } else {
                        self.init();
                    }
                }, 1000);
            };

            self.init();
        });
